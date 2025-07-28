package com.example.calmly.uiScreens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.example.calmly.data.SoundCategory
import com.example.calmly.uiScreens.components.CategoryContentScreen
import com.example.calmly.uiScreens.components.SoundCard
import com.example.calmly.viewmodel.MainViewModel
@Composable
fun MeditationScreen(viewModel: MainViewModel, navController: NavHostController) {
    val meditationSound = viewModel.sounds.filter { it.category == SoundCategory.MEDITATION }
    val currentPlaySoundId=viewModel.currentPlayingSoundId.collectAsState()
    CategoryContentScreen(sounds=meditationSound,viewModel=viewModel, categoryName = "Meditation")
}
