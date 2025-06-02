package com.yuaatn.instagram_notes.ui.navigation

sealed class Screen(val rout: String) {
    object Home: Screen("home_screen")
    object Profile: Screen("profile_screen")
    object Setting: Screen("setting_screen")
}