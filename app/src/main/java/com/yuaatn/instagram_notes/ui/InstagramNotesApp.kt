package com.yuaatn.instagram_notes.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.yuaatn.instagram_notes.ui.navigation.BottomNavigationBar
import com.yuaatn.instagram_notes.ui.navigation.Screen
import com.yuaatn.instagram_notes.ui.screens.HomeScreen
import com.yuaatn.instagram_notes.ui.screens.ProfileScreen
import com.yuaatn.instagram_notes.ui.screens.SettingScreen

@Composable
fun InstagramNotesApp(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->

        val graph =
            navController.createGraph(startDestination = Screen.Home.rout) {
                composable(route = Screen.Home.rout) {
                    HomeScreen()
                }
                composable(route = Screen.Setting.rout) {
                    SettingScreen()
                }
                composable(route = Screen.Profile.rout) {
                    ProfileScreen()
                }
            }
        NavHost(
            navController = navController,
            graph = graph,
            modifier = Modifier.padding(innerPadding)
        )

    }
}