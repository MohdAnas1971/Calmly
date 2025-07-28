package com.example.calmly.uiScreens.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calmly.data.Sound
import com.example.calmly.ui.theme.DesaturatedBlue
import com.example.calmly.ui.theme.SoftWhite
import com.example.calmly.viewmodel.MainViewModel


@Composable
 fun CategoryContentScreen(
    viewModel: MainViewModel,
    sounds: List<Sound>,
    categoryName: String
) {

    val currentPlaySoundId=viewModel.currentPlayingSoundId.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(DesaturatedBlue)
            .padding(top = 40.dp, start = 8.dp, end = 8.dp, bottom = 120.dp)
    ) {
        Text(categoryName, style = MaterialTheme.typography.headlineLarge, color = SoftWhite)
        Spacer(modifier = Modifier.height(40.dp))

        LazyColumn(Modifier.fillMaxWidth()) {

            item {
                AutoSlidingImageRow(sounds)
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
            }
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
}