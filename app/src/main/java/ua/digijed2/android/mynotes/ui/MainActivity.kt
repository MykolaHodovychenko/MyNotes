package ua.digijed2.android.mynotes.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ua.digijed2.android.mynotes.MyApplication
import ua.digijed2.android.mynotes.R
import ua.digijed2.android.mynotes.databinding.ActivityMainBinding
import ua.digijed2.android.mynotes.ui.adapters.NotesAdapter
import ua.digijed2.android.mynotes.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels() {
        MainViewModel.MainViewModelFactory((application as MyApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.list.layoutManager = LinearLayoutManager(this)
        val adapter = NotesAdapter(layoutInflater) {
            val i = Intent(this, NoteActivity::class.java)
            i.putExtra("note_id", it.id)
            startActivity(i)
        }
        binding.list.adapter = adapter

        binding.fab.setOnClickListener {
            val i = Intent(this, NoteActivity::class.java)
            startActivity(i)
        }

        viewModel.notes.observe(this) {
            adapter.submitList(it)
        }
    }
}