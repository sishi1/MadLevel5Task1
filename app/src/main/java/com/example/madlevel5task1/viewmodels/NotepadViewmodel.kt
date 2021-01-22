package com.example.madlevel5task1.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task1.model.Note
import com.example.madlevel5task1.repository.NotepadRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class NotepadViewmodel(application: Application): AndroidViewModel(application) {

    private val notepadRepository = NotepadRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val note = notepadRepository.getNotepad()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun updateNote(title: String, text: String) {
        val newNote = Note(
            id = note.value?.id,
            title = title,
            lastUpdated = Date(),
            text = text
        )

        if (isNotValid(newNote)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    notepadRepository.updateNotepad(newNote)
                }
                success.value = true
            }
        }
    }

    private fun isNotValid(note: Note): Boolean {
        return when {
            note.title.isBlank() -> {
                error.value = "Title must not be empty."
                false
            }
            else -> true
        }
    }
}