package com.yuaatn.instagram_notes.data.local

import com.yuaatn.instagram_notes.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import org.slf4j.LoggerFactory

class FileNotebookProxy(
    private val fileNotebook: FileNotebook
) : NotesRepository {
    private val logger = LoggerFactory.getLogger(FileNotebookProxy::class.java)

    override val notes: Flow<List<Note>> get() = fileNotebook.notes

    override fun addNote(note: Note) {
        fileNotebook.addNote(note)
        logger.debug("Добавлена заметка: ${note.title}")
    }

    override fun getNoteByUid(uid: String): Flow<Note> {
        logger.debug("Получение заметки по UID: $uid")
        return fileNotebook.getNoteByUid(uid)
    }

    override fun updateNote(note: Note) {
        fileNotebook.updateNote(note)
        logger.debug("Обновлена заметка: ${note.title} (UID: ${note.uid})")
    }

    override fun deleteNote(uid: String) {
        fileNotebook.deleteNote(uid)
        logger.debug("Удаление заметки UID=$uid")
    }

    override suspend fun saveToFile() {
        fileNotebook.saveToFile()
        logger.debug("Сохранено ${fileNotebook.notes.count()} заметок в файл")
    }

    override suspend fun loadFromFile() {
        fileNotebook.loadFromFile()
        logger.debug("Загружено ${fileNotebook.notes.count()} заметок из файла")
    }
}