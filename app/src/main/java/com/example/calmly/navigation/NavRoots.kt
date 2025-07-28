package com.example.calmly.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class NavRoots{
    @Serializable
   data object MainScreen

    @Serializable
    data object SleepScreen

    @Serializable
    data object MeditationScreen
}