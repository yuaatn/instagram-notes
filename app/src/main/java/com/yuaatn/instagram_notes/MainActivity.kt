package com.yuaatn.instagram_notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.yuaatn.instagram_notes.ui.InstagramNotesApp
import com.yuaatn.instagram_notes.ui.theme.InstagramNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            InstagramNotesTheme {
                InstagramNotesApp()
            }
        }
    }
}

