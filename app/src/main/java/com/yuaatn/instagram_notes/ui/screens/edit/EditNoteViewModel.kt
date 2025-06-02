package com.yuaatn.instagram_notes.ui.screens.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuaatn.instagram_notes.data.NotesRepository
import com.yuaatn.instagram_notes.ui.screens.add.NoteEntity
import com.yuaatn.instagram_notes.ui.screens.add.toNote
import com.yuaatn.instagram_notes.ui.screens.add.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val jsonRepository: NotesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NoteEditState())
    val uiState: StateFlow<NoteEditState> = _uiState

    fun processAction(action: NoteEditAction) {
        when (action) {
            is NoteEditAction.FetchNote -> fetchNoteById(action.noteId)
            is NoteEditAction.ModifyNoteEntity -> updateNoteEntity(action.note)
            NoteEditAction.SaveChanges -> saveNoteChanges()
            NoteEditAction.RemoveNote -> removeNote()
            NoteEditAction.ClearDeletionFlag -> clearDeletionFlag()
        }
    }

    private fun fetchNoteById(id: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            jsonRepository.getNoteByUid(id)
                .filterNotNull()
                .map { note ->
                    _uiState.value.copy(
                        currentNote = note.toUiState(),
                        isValidEntry = true,
                        loading = false
                    )
                }
                .first()
                .let { newState ->
                    _uiState.value = newState
                }
        }
    }

    private fun updateNoteEntity(note: NoteEntity) {
        _uiState.value = _uiState.value.copy(
            currentNote = note,
            isValidEntry = validateNoteInput(note)
        )
    }

    private fun validateNoteInput(note: NoteEntity = _uiState.value.currentNote): Boolean {
        return note.title.isNotBlank() && note.content.isNotBlank()
    }

    private fun saveNoteChanges() {
        viewModelScope.launch {
            if (validateNoteInput()) {
                jsonRepository.updateNote(_uiState.value.currentNote.toNote())
                jsonRepository.saveToFile()
            }
        }
    }

    private fun removeNote() {
        viewModelScope.launch {
            jsonRepository.deleteNote(_uiState.value.currentNote.toNote().uid)
            _uiState.value = _uiState.value.copy(noteDeleted = true)
        }
    }

    private fun clearDeletionFlag() {
        _uiState.value = _uiState.value.copy(noteDeleted = false)
    }
}
