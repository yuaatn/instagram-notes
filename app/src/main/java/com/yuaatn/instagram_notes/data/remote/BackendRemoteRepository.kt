package com.yuaatn.instagram_notes.data.remote

import android.util.Log
import com.yuaatn.instagram_notes.data.remote.model.UidResponse
import com.yuaatn.instagram_notes.data.remote.util.ResultWrapper
import com.yuaatn.instagram_notes.model.Note

class BackendRemoteRepository : RemoteRepository {
    private companion object {
        const val TAG = "BackendRemoteRepo"
    }

    override suspend fun fetchNotes(): ResultWrapper<List<Note>> {
        Log.w(TAG, "fetchNotes() - Not implemented yet")
        return ResultWrapper.Error(NotImplementedError("fetchNotes not implemented"))
    }

    override suspend fun fetchNoteById(id: String): ResultWrapper<Note> {
        Log.w(TAG, "fetchNoteById($id) - Not implemented yet")
        return ResultWrapper.Error(NotImplementedError("fetchNoteById not implemented"))
    }

    override suspend fun createNote(note: Note): ResultWrapper<UidResponse> {
        Log.w(TAG, "createNote(${note.title}) - Not implemented yet")
        return ResultWrapper.Error(NotImplementedError("createNote not implemented"))
    }

    override suspend fun updateNote(note: Note): ResultWrapper<UidResponse> {
        Log.w(TAG, "updateNote(${note.uid}) - Not implemented yet")
        return ResultWrapper.Error(NotImplementedError("updateNote not implemented"))
    }

    override suspend fun removeNoteById(id: String): ResultWrapper<Unit> {
        Log.w(TAG, "removeNoteById($id) - Not implemented yet")
        return ResultWrapper.Error(NotImplementedError("removeNoteById not implemented"))
    }

    override suspend fun patchNotes(notes: List<Note>): ResultWrapper<List<Note>> {
        Log.w(TAG, "patchNotes(${notes.size} notes) - Not implemented yet")
        return ResultWrapper.Error(NotImplementedError("patchNotes not implemented"))
    }

    override suspend fun fetchNotesWithThreshold(threshold: Int?): ResultWrapper<List<Note>> {
        Log.w(TAG, "fetchNotesWithThreshold($threshold) - Not implemented yet")
        return ResultWrapper.Error(NotImplementedError("fetchNotesWithThreshold not implemented"))
    }

    override suspend fun clearNotes() {
        Log.w(TAG, "clearNotes() - Not implemented yet")
        throw NotImplementedError("clearNotes not implemented")
    }
}