package com.yuaatn.instagram_notes.data

import android.content.Context
import com.yuaatn.instagram_notes.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import org.json.JSONArray
import java.io.File

class FileNotebook(context: Context) : NotesRepository {

    private val file = File(context.filesDir, "notes.json")
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    override val notes: Flow<List<Note>> get() = _notes

    override fun addNote(note: Note) {
        _notes.value += note
    }

    override fun getNoteByUid(uid: String): Flow<Note> =
        flow {
            val habit = checkNotNull(_notes.value.find { it.uid == uid })
            emit(habit)
        }

    override fun updateNote(note: Note) {
        val updatedNotes = _notes.value.toMutableList()
        val index = updatedNotes.indexOfFirst { it.uid == note.uid }
        if (index != -1) {
            updatedNotes[index] = note
            _notes.value = updatedNotes
        }
    }

    override fun deleteNote(uid: String) {
        _notes.value = _notes.value.filterNot { it.uid == uid }
    }

    override suspend fun saveToFile() {
        val jsonArray = JSONArray()
        _notes.value.forEach { jsonArray.put(it.json) }
        file.writeText(jsonArray.toString())
    }

    override suspend fun loadFromFile() {
        if (!file.exists()) return

        val jsonText = file.readText()
        val array = JSONArray(jsonText)
        val loadedNotes = mutableListOf<Note>()
        for (i in 0 until array.length()) {
            val jsonNote = array.getJSONObject(i)
            Note.parse(jsonNote)?.let {
                loadedNotes.add(it)
            }
        }
        _notes.value = loadedNotes
    }
}