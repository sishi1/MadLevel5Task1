package com.example.madlevel5task1.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.madlevel5task1.repository.NotepadRepository

class notepadViewmodel(application: Application): AndroidViewModel(application) {

    private val notepadRepository = NotepadRepository(application.applicationContext)

    val note = notepadRepository.getNotepad()
}