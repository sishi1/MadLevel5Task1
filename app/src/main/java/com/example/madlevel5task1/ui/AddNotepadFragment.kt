package com.example.madlevel5task1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task1.R
import com.example.madlevel5task1.viewmodels.NotepadViewmodel
import kotlinx.android.synthetic.main.fragment_add_notepad.*
import kotlinx.android.synthetic.main.fragment_notepad.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddNotepadFragment : Fragment() {

    private val viewModel: NotepadViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_notepad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddNote.setOnClickListener {
            saveNote()
        }
        observeNote()

    }

    private fun observeNote() {

        viewModel.note.observe(viewLifecycleOwner, Observer { note ->
            note?.let {
                textInputLayout.editText?.setText(it.title)
                textInputLayout2.editText?.setText(it.text)
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.success.observe(viewLifecycleOwner, Observer { success ->
            findNavController().popBackStack()
        })

    }

    private fun saveNote() {
        viewModel.updateNote(
            textInputLayout.editText?.text.toString(),
            textInputLayout2.editText?.text.toString()
        )
    }
}