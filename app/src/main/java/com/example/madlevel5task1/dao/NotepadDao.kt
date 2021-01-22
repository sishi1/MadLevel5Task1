package com.example.madlevel5task1.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.madlevel5task1.model.Note

@Dao
interface NotepadDao {

    @Query("SELECT * FROM notepadTable LIMIT 1")
    fun getNotepad(): LiveData<Note?>

    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)
}