package com.calmly_example.calmly.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.calmly_example.calmly.MainActivity
import com.calmly_example.calmly.R

class MusicService : Service() {

    private var mediaPlayer: MediaPlayer? = null
    
    companion object {
        const val CHANNEL_ID = "music_playback_channel"
        const val NOTIFICATION_ID = 1
        
        const val ACTION_PLAY = "com.calmly.action.PLAY"
        const val ACTION_PAUSE = "com.calmly.action.PAUSE"
        const val ACTION_RESUME = "com.calmly.action.RESUME"
        const val ACTION_STOP = "com.calmly.action.STOP"
        
        const val EXTRA_SOUND_RES_ID = "extra_sound_res_id"
        const val EXTRA_SOUND_NAME = "extra_sound_name"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_PLAY -> {
                val resId = intent.getIntExtra(EXTRA_SOUND_RES_ID, -1)
                val name = intent.getStringExtra(EXTRA_SOUND_NAME) ?: "Calmly Sound"
                if (resId != -1) {
                    playMusic(resId, name)
                }
            }
            ACTION_PAUSE -> pauseMusic()
            ACTION_RESUME -> resumeMusic()
            ACTION_STOP -> stopForegroundService()
        }
        return START_STICKY
    }

    private fun playMusic(soundResId: Int, soundName: String) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(this, soundResId).apply {
            isLooping = true
            start()
        }
        startForegroundWithNotification(soundName, true)
    }

    private fun pauseMusic() {
        mediaPlayer?.pause()
        startForegroundWithNotification("Paused", false)
    }

    private fun resumeMusic() {
        mediaPlayer?.start()
        startForegroundWithNotification("Playing", true)
    }

    private fun stopForegroundService() {
        mediaPlayer?.stop()
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun startForegroundWithNotification(status: String, isPlaying: Boolean) {
        val notification = buildNotification(status, isPlaying)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(NOTIFICATION_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK)
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    private fun buildNotification(content: String, isPlaying: Boolean): Notification {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val actionIntent = Intent(this, MusicService::class.java).apply {
            action = if (isPlaying) ACTION_PAUSE else ACTION_RESUME
        }
        val actionPendingIntent = PendingIntent.getService(this, 1, actionIntent, PendingIntent.FLAG_IMMUTABLE)

        val stopIntent = Intent(this, MusicService::class.java).apply { action = ACTION_STOP }
        val stopPendingIntent = PendingIntent.getService(this, 2, stopIntent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Calmly")
            .setContentText(content)
            .setSmallIcon(R.drawable.outline_play_circle_24)
            .setOngoing(isPlaying)
            .setContentIntent(pendingIntent)
            .addAction(
                if (isPlaying) R.drawable.outline_pause_circle_24 else R.drawable.outline_play_circle_24,
                if (isPlaying) "Pause" else "Resume",
                actionPendingIntent
            )
            .addAction(android.R.drawable.ic_menu_close_clear_cancel, "Stop", stopPendingIntent)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(0, 1))
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }
}

