package com.example.calmly.uiScreens


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.calmly.data.SoundCategory
import com.example.calmly.uiScreens.components.CategoryContentScreen
import com.example.calmly.viewmodel.MainViewModel

@Composable
fun SleepScreen(viewModel: MainViewModel) {
    val sleepSounds = viewModel.sounds.filter { it.category == SoundCategory.SLEEP }
    viewModel.currentPlayingSoundId.collectAsState()
    CategoryContentScreen(viewModel = viewModel, sounds = sleepSounds, categoryName = "Sleep")
}



