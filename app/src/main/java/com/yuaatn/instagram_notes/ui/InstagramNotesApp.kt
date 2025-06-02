package com.yuaatn.instagram_notes.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.navArgument
import com.yuaatn.instagram_notes.navigation.BottomNavigationBar
import com.yuaatn.instagram_notes.navigation.Screen
import com.yuaatn.instagram_notes.ui.screens.ProfileScreen
import com.yuaatn.instagram_notes.ui.screens.SettingScreen
import com.yuaatn.instagram_notes.ui.screens.add.NoteCreationScreen
import com.yuaatn.instagram_notes.ui.screens.edit.NoteEditScreen
import com.yuaatn.instagram_notes.ui.screens.home.HomeScreen

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
            navController.createGraph(startDestination = Screen.Home.route) {
                composable(route = Screen.Home.route) {
                    HomeScreen(
                        onCreateNote = { navController.navigate(Screen.AddNote.route) },
                        onModifyNote = { noteUid ->
                            navController.navigate("${Screen.EditNote.route}/$noteUid")
                        }
                    )
                }

                composable(route = Screen.Setting.route) {
                    SettingScreen()
                }

                composable(route = Screen.Profile.route) {
                    ProfileScreen(
                        avatarUrl = "https://sun9-57.userapi.com/impg/cPbQdKwQ99V3o1aWyoyGV9xJErOjEEFgND4_wQ/2rfVkq02OLI.jpg?size=1620x2160&quality=95&sign=91debc45437a2c835e585ff5c4b23b87&type=album",
                        username = "yuaatn",
                        bio = "Android developer | Tech enthusiast | Coffee lover ☕"
                    )
                }

                composable(
                    route = Screen.AddNote.route
                ) {
                    NoteCreationScreen(
                        onBack = { navController.popBackStack() },
                        viewModel = hiltViewModel()
                    )
                }
                composable(
                    route = "${Screen.EditNote.route}/{noteUid}",
                    arguments = listOf(
                        navArgument("noteUid") {
                            type = NavType.StringType
                        }
                    )
                ) { backStackEntry ->
                    val noteUid = backStackEntry.arguments?.getString("noteUid")
                    NoteEditScreen(
                        noteId = noteUid,
                        onNavigateBack = { navController.popBackStack() },
                        viewModel = hiltViewModel()
                    )
                }
            }
        NavHost(
            navController = navController,
            graph = graph,
            modifier = Modifier.padding(innerPadding)
        )

    }
}