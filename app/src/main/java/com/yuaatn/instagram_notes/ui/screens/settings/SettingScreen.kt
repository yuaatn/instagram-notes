package com.yuaatn.instagram_notes.ui.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.yuaatn.instagram_notes.ui.screens.home.HomeAction

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {


    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Setting Screen",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}