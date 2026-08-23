package com.calmly_example.calmly.uiScreens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.calmly_example.calmly.data.Sound
import com.calmly_example.calmly.R
import com.calmly_example.calmly.ui.theme.DesaturatedBlueDark
import com.calmly_example.calmly.viewmodel.MainViewModel

@Composable
fun SoundCard(sound: Sound, isThisSoundPlaying: Boolean, isPlay: Boolean, onPlayPause: () -> Unit) {
    val icon = if (isThisSoundPlaying && isPlay) {
        painterResource(R.drawable.outline_pause_circle_24)
    } else {
        painterResource(R.drawable.outline_play_circle_24)
    }

    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(
            containerColor = DesaturatedBlueDark,
            contentColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = sound.imageRes),
                modifier = Modifier.size(64.dp),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = sound.name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = onPlayPause) {
                Icon(
                    painter = icon,
                    tint = Color.White,
                    contentDescription = if (isThisSoundPlaying && isPlay) "Pause" else "Play"
                )
            }
        }
    }
}

fun LazyListScope.soundCardList(
    sounds: List<Sound>,
    viewModel: MainViewModel
) {
    items(sounds, key = { it.id }) { sound ->
        val currentPlaySoundId = viewModel.currentPlayingSoundId.collectAsState()
        val isPlaying = viewModel.isPlaying

        SoundCard(
            sound = sound,
            isThisSoundPlaying = currentPlaySoundId.value == sound.id,
            isPlay = isPlaying.value
        ) {
            viewModel.onSoundClicked(sound)
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}
