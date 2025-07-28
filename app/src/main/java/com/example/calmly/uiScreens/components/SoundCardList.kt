package com.example.calmly.uiScreens.components


import android.nfc.cardemulation.CardEmulation
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.calmly.data.Sound
import com.example.calmly.ui.theme.CharcoalBlack
import com.example.calmly.viewmodel.MainViewModel
import com.example.calmly.R
import com.example.calmly.ui.theme.DesaturatedBlueDark


@Composable
fun SoundCard(sound: Sound, isPlaying: Boolean, onPlayPause: () -> Unit) {
    ElevatedCard(
        colors = CardColors(
            containerColor = DesaturatedBlueDark,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor =Color.Unspecified

        ),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp, pressedElevation =3.dp, focusedElevation = 5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(painterResource(id = sound.imageRes), modifier = Modifier.size(100.dp), contentDescription = null)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = sound.name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = onPlayPause) {
                Icon(painter = if (isPlaying) painterResource(R.drawable.outline_pause_circle_24) else painterResource(R.drawable.outline_play_circle_24), tint = Color.White, contentDescription = null)
            }
        }
    }
}


@Composable
fun SoundCardList(sounds:List<Sound>,viewModel: MainViewModel) {
    val currentPlaySoundId=viewModel.currentPlayingSoundId.collectAsState()

    LazyColumn {

        items(sounds){sound->
            SoundCard(
                sound = sound,
                isPlaying = currentPlaySoundId.value==sound.id
            ) {
                viewModel.onSoundClicked(sound)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}



