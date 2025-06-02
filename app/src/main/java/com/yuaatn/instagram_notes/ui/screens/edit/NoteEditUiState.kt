package com.yuaatn.instagram_notes.ui.screens.edit

import com.yuaatn.instagram_notes.ui.screens.add.NoteEntity

data class NoteEditUiState(
    val currentNote: NoteEntity = NoteEntity(),
    val isValidEntry: Boolean = false,
    val loading: Boolean = false,
    val noteDeleted: Boolean = false
)
