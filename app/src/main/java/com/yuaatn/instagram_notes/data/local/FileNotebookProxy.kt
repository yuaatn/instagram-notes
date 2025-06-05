package com.yuaatn.instagram_notes.data.local

import com.yuaatn.instagram_notes.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import org.slf4j.LoggerFactory
import javax.inject.Inject

class FileNotebookProxy @Inject constructor(
    private val fileNotebookImpl: FileNotebook
) : FileNotebook {
    private val logger = LoggerFactory.getLogger(FileNotebookProxy::class.java)

    override val notes: Flow<List<Note>> get() = fileNotebookImpl.notes

    override fun addNote(note: Note) {
        fileNotebookImpl.addNote(note)
        logger.debug("Добавлена заметка: ${note.title}")
    }

    override fun getNoteByUid(uid: String): Flow<Note> {
        logger.debug("Получение заметки по UID: $uid")
        return fileNotebookImpl.getNoteByUid(uid)
    }

    override fun updateNote(note: Note) {
        fileNotebookImpl.updateNote(note)
        logger.debug("Обновлена заметка: ${note.title} (UID: ${note.uid})")
    }

    override fun deleteNote(uid: String) {
        fileNotebookImpl.deleteNote(uid)
        logger.debug("Удаление заметки UID=$uid")
    }

    override suspend fun saveToFile() {
        fileNotebookImpl.saveToFile()
        logger.debug("Сохранено ${fileNotebookImpl.notes.count()} заметок в файл")
    }

    override suspend fun loadFromFile() {
        fileNotebookImpl.loadFromFile()
        logger.debug("Загружено ${fileNotebookImpl.notes.count()} заметок из файла")
    }
}