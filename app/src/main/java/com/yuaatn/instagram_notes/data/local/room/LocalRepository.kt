package com.yuaatn.instagram_notes.data.local.room

import com.yuaatn.instagram_notes.model.Note
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    val notes: Flow<List<Note>>
    suspend fun addNote(note: Note)
    suspend fun getNoteByUid(uid: String): Note?
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(uid: String)
}