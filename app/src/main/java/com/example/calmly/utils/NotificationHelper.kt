package com.example.calmly.utils


import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.calmly.R


class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    private val CHANNEL_ID = "music_channel"
    private val NOTIFICATION_ID = 1

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        Log.d("hello","in action :$action")

        when (action) {

            "PLAY" -> {
                val soundResId = intent.getIntExtra("SOUND_RES_ID", -1)
                Log.d("hello","in service:$soundResId")
                if (soundResId != -1) {
                    playMusic(soundResId)

                }
            }
           // "PAUSE" -> pauseMusic()
            "STOP" -> stopSelf()
        }

       /* if (!::mediaPlayer.isInitialized) {
            mediaPlayer = MediaPlayer.create(this, R.raw.your_song_file)
        }*/

        startForeground(NOTIFICATION_ID, buildNotification())
        return START_NOT_STICKY
    }

 /*   private fun playMusic(soundResId: Int) {
        Log.d("hello","in ::mediaPlayer.isInitialized :${::mediaPlayer.isInitialized}")

        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release()

        }

        mediaPlayer = MediaPlayer.create(this, soundResId)
        mediaPlayer.start()

        startForeground(NOTIFICATION_ID, buildNotification())
    }*/
 private fun playMusic(soundResId: Int) {
     Log.d("hello", "in ::mediaPlayer.isInitialized :${::mediaPlayer.isInitialized}")

     try {
         if (::mediaPlayer.isInitialized) {
             mediaPlayer.stop()
             mediaPlayer.release()
         }else{
             mediaPlayer = MediaPlayer.create(this, soundResId)

         }

         mediaPlayer.setOnPreparedListener {
             it.start()
             startForeground(NOTIFICATION_ID, buildNotification())
         }
         mediaPlayer.setOnCompletionListener {
             stopSelf()  // Stop service after sound finishes if needed
         }

         // If already prepared (most likely for raw resources), start immediately
         if (!mediaPlayer.isPlaying) {
             mediaPlayer.start()
             startForeground(NOTIFICATION_ID, buildNotification())
         }

     } catch (e: Exception) {
         Log.e("MusicService", "Error playing sound: ${e.message}")
     }
 }


    private fun pauseMusic() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
        updateNotification()
    }

    private fun buildNotification(): Notification {
        val playIntent = Intent(this, MusicService::class.java).apply {
            action = "PLAY"
        }
        val pauseIntent = Intent(this, MusicService::class.java).apply {
            action = "PAUSE"
        }

        val playPendingIntent = PendingIntent.getService(this, 0, playIntent, PendingIntent.FLAG_IMMUTABLE)
        val pausePendingIntent = PendingIntent.getService(this, 1, pauseIntent, PendingIntent.FLAG_IMMUTABLE)

        val isPlaying = mediaPlayer.isPlaying

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Song Title")
            .setContentText("Artist Name")
            .setSmallIcon(R.drawable.outline_play_circle_24)
            .addAction(
                if (isPlaying) R.drawable.outline_pause_circle_24 else R.drawable.outline_play_circle_24,
                if (isPlaying) "Pause" else "Play",
                if (isPlaying) pausePendingIntent else playPendingIntent
            )
            .setOnlyAlertOnce(true)
            .setOngoing(isPlaying)
            .setPriority(NotificationCompat.PRIORITY_LOW)

        return builder.build()
    }

    private fun updateNotification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, buildNotification())
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
        super.onDestroy()
    }
}
