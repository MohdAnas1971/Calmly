package com.example.calmly.uiScreens.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
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
import com.example.calmly.R
import com.example.calmly.data.Sound
import com.example.calmly.ui.theme.DesaturatedBlueDark
import com.example.calmly.viewmodel.MainViewModel


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



