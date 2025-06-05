package com.yuaatn.instagram_notes.data.local.room.repository

import com.yuaatn.instagram_notes.data.local.LocalRepository
import com.yuaatn.instagram_notes.data.local.room.dao.NoteDao
import com.yuaatn.instagram_notes.data.local.room.mappers.toDomain
import com.yuaatn.instagram_notes.data.local.room.mappers.toEntity
import com.yuaatn.instagram_notes.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class RoomRepository(
    private val dao: NoteDao,
) : LocalRepository {

    override val notes: Flow<List<Note>> = dao.getAll().map { entities ->
        entities.map { it.toDomain() }
    }

    override suspend fun updateNote(note: Note) {
        val updatedRows = dao.updateByUid(
            uid = note.uid,
            title = note.title,
            content = note.content,
            color = note.color,
            importance = note.importance,
            deadline = note.deadline
        )
        if (updatedRows == 0) {
            dao.insert(note.toEntity())
        }
    }

    override suspend fun addNote(note: Note) {
        val updatedRows = dao.updateByUid(
            uid = note.uid,
            title = note.title,
            content = note.content,
            color = note.color,
            importance = note.importance,
            deadline = note.deadline
        )

        if (updatedRows == 0) {
            dao.insert(note.toEntity())
        }
    }

    override suspend fun getNoteByUid(uid: String): Flow<Note> {
        return dao.getByUid(uid)
            .filterNotNull()
            .map { it.toDomain() }
    }

    override suspend fun deleteNote(uid: String) {
        dao.deleteByUid(uid)
    }


    override suspend fun saveToFile() {
        TODO("Not yet implemented")
    }

    override suspend fun loadFromFile() {
        TODO("Not yet implemented")
    }

    override suspend fun updateNotes(remoteNotes: List<Note>) {
        TODO("Not yet implemented")
    }
}
