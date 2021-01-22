package com.example.madlevel5task1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.madlevel5task1.R
import com.example.madlevel5task1.viewmodels.notepadViewmodel
import kotlinx.android.synthetic.main.fragment_notepad.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NotepadFragment : Fragment() {

    private val viewmodel: notepadViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notepad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAddNoteResult()

    }

    private fun observeAddNoteResult() {
        viewmodel.note.observe(viewLifecycleOwner, Observer { note ->
            note?.let {
                tvNotepadTitle.text = it.title
                tvDate.text = getString(R.string.date, it.lastUpdated.toString())
                tvNotepadText.text = it.text
            }
        })
    }
}