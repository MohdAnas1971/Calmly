package com.calmly_example.calmly.viewmodel


import android.app.Application
import android.content.Intent
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.calmly_example.calmly.data.Sound
import com.calmly_example.calmly.data.SoundRepository
import com.calmly_example.calmly.service.MusicService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext

    // List of all sounds from repository
    val sounds: List<Sound> = SoundRepository.meditationSounds + SoundRepository.sleepSounds

    // List of popular sound on calmly
    val popularOnCalmly: List<Sound> = SoundRepository.popularOnCalmly

    private var _isPlaying = mutableStateOf(false)
    val isPlaying = _isPlaying

    private val _currentPlayingSoundId = MutableStateFlow<Int?>(null)
    val currentPlayingSoundId: StateFlow<Int?> = _currentPlayingSoundId

    fun onSoundClicked(sound: Sound) {
        if (_currentPlayingSoundId.value == sound.id) {
            if (isPlaying.value) {
                sendCommandToService(MusicService.ACTION_PAUSE)
                _isPlaying.value = false
            } else {
                sendCommandToService(MusicService.ACTION_RESUME)
                _isPlaying.value = true
            }
        } else {
            playSound(sound)
        }
    }

    private fun sendCommandToService(action: String) {
        val intent = Intent(context, MusicService::class.java).apply {
            this.action = action
        }
        context.startService(intent)
    }

    private fun playSound(sound: Sound) {
        val intent = Intent(context, MusicService::class.java).apply {
            action = MusicService.ACTION_PLAY
            putExtra(MusicService.EXTRA_SOUND_RES_ID, sound.soundRes)
            putExtra(MusicService.EXTRA_SOUND_NAME, sound.name)
        }
        ContextCompat.startForegroundService(context, intent)
        _currentPlayingSoundId.value = sound.id
        _isPlaying.value = true
    }

    private fun stopCurrentSound() {
        sendCommandToService(MusicService.ACTION_STOP)
        _currentPlayingSoundId.value = null
        _isPlaying.value = false
    }

}

