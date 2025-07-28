package com.example.calmly.viewmodel


import android.app.Application
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.example.calmly.data.Sound
import com.example.calmly.data.SoundRepository
import com.example.calmly.utils.MusicService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext

    // List of all sounds from repository
    val sounds: List<Sound> = SoundRepository.meditationSounds + SoundRepository.sleepSounds
     val popularOnCalmly: List<Sound> = SoundRepository.popularOnCalmly
    // ID of currently playing sound

    private var _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()
    private val _currentPlayingSoundId = MutableStateFlow<Int?>(null)
    val currentPlayingSoundId: StateFlow<Int?> = _currentPlayingSoundId

    private var mediaPlayer: MediaPlayer? = null

    /**
     * Handle sound click — play or pause
     */
    fun onSoundClicked(sound: Sound) {
        if (_currentPlayingSoundId.value == sound.id) {
            // Pause if same sound clicked again
            stopCurrentSound()
        } else {
            playSound(sound)
        }
    }

    private fun playSound(sound: Sound) {

        if (isPlaying.value){
            stopCurrentSound()
        }else{
            val intent = Intent(context, MusicService::class.java).apply {
                action = "PLAY"
                Log.d("hello","in in viewModle: ${sound.soundRes}")
                putExtra("SOUND_RES_ID", sound.soundRes)
            }

            ContextCompat.startForegroundService(context, intent)

            _currentPlayingSoundId.value = sound.id
            _isPlaying.value=true
        }

    }

    private fun stopCurrentSound(){
        val intent = Intent(context, MusicService::class.java).apply {
            action = "STOP"
        }
        context.startService(intent)
        _currentPlayingSoundId.value = null

        _isPlaying.value=false
       /* mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        _currentPlayingSoundId.value = null*/


    }

    override fun onCleared() {
        super.onCleared()
        stopCurrentSound()
    }
}
