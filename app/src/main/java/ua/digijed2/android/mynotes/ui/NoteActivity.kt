package ua.digijed2.android.mynotes.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import ua.digijed2.android.mynotes.MyApplication
import ua.digijed2.android.mynotes.databinding.ActivityNoteBinding
import ua.digijed2.android.mynotes.model.Note
import ua.digijed2.android.mynotes.ui.viewmodels.NoteViewModel
import java.util.*

class NoteActivity : AppCompatActivity() {

    private val viewModel: NoteViewModel by viewModels() {
        NoteViewModel.NoteViewModelFactory((application as MyApplication).repository)
    }

    private var editMode = false

    private val oldNote = MutableLiveData<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getLongExtra("note_id", -1L)
        editMode = id != -1L

        if (editMode)
            viewModel.getNoteById(oldNote, id)

        oldNote.observe(this) {
            it?.let {
                binding.title.editText?.setText(it.title)
                binding.text.editText?.setText(it.text)
            } ?: throw IllegalArgumentException("note_id is incorrect")
        }

        binding.save.setOnClickListener {
            val date = Date()

            if (editMode) {
                oldNote.value?.let {
                    it.title = binding.title.editText?.text.toString()
                    it.text = binding.text.editText?.text.toString()
                    it.updated = date
                    viewModel.updateNote(it)
                }
            } else {
                val note = Note(
                    title = binding.title.editText?.text.toString(),
                    text = binding.text.editText?.text.toString(),
                    created = date,
                    updated = date
                )
                viewModel.addNote(note)
            }
            finish()
        }
    }
}