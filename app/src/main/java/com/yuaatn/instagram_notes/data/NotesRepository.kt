package com.yuaatn.instagram_notes.data

import com.yuaatn.instagram_notes.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    val notes: Flow<List<Note>>

    fun addNote(note: Note)
    fun getNoteByUid(uid: String): Flow<Note>
    fun updateNote(note: Note)
    fun deleteNote(uid: String)

    suspend fun saveToFile()
    suspend fun loadFromFile()
}