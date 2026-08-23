package com.calmly_example.calmly.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.calmly_example.calmly.uiScreens.MainScreen
import com.calmly_example.calmly.uiScreens.MeditationScreen
import com.calmly_example.calmly.uiScreens.SleepScreen
import com.calmly_example.calmly.viewmodel.MainViewModel


@Composable
fun CalmlyNavGraph() {

    val navController: NavHostController= rememberNavController()
    val viewModel: MainViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) {
            MainScreen(viewModel, navController)
        }

        composable(Screen.Sleep.route) {
            SleepScreen(viewModel = viewModel)
        }

        composable(Screen.Meditation.route) {
            MeditationScreen(viewModel = viewModel)
        }
    }
}

