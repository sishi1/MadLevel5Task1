package com.example.madlevel5task1.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.madlevel5task1.dao.NotepadDao
import com.example.madlevel5task1.database.NotepadRoomDatabase
import com.example.madlevel5task1.model.Note

class NotepadRepository(context: Context) {

    private val notepadDao: NotepadDao

    init {
        val database = NotepadRoomDatabase.getDatabase(context)
        notepadDao = database!!.notepadDao()
    }

    fun getNotepad(): LiveData<Note?> {
        return notepadDao.getNotepad()
    }

    suspend fun updateNotepad(note: Note) {
        return notepadDao.updateNote(note)
    }
}