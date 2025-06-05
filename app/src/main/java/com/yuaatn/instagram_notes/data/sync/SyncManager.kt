package com.yuaatn.instagram_notes.data.sync

import com.yuaatn.instagram_notes.model.Note

interface SyncManager {
    suspend fun synchronize()
    suspend fun syncOnCreate(note: Note)
    suspend fun syncOnUpdate(note: Note)
    suspend fun syncOnDelete(uid: String)
}

interface ConflictResolver {
    suspend fun resolveConflicts(localNotes: List<Note>, remoteNotes: List<Note>)
}
