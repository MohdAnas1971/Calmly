package com.example.calmly.uiScreens

import androidx.compose.runtime.Composable
import com.example.calmly.data.SoundCategory
import com.example.calmly.uiScreens.components.CategoryContentScreen
import com.example.calmly.viewmodel.MainViewModel

@Composable
fun MeditationScreen(viewModel: MainViewModel) {
    val meditationSound = viewModel.sounds.filter { it.category == SoundCategory.MEDITATION }
    CategoryContentScreen(sounds=meditationSound,viewModel=viewModel, categoryName = "Meditation")
}
