package com.calmly_example.calmly.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Sleep : Screen("sleep")
    object Meditation : Screen("meditation")
}
