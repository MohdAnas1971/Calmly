package com.example.calmly.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calmly.uiScreens.MainScreen
import com.example.calmly.uiScreens.MeditationScreen
import com.example.calmly.uiScreens.SleepScreen
import com.example.calmly.viewmodel.MainViewModel


@Composable
fun CalmlyNavGraph() {

    val navController: NavHostController= rememberNavController()
    val viewModel: MainViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "main") {

        composable("main") {
            MainScreen( viewModel, navController)
        }

        composable("SleepScreen"){
            SleepScreen(viewModel = viewModel)
        }

        composable("MeditationScreen"){
            MeditationScreen(viewModel = viewModel)
        }
    }
}
