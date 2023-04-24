package ua.digijed2.android.mynotes.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.digijed2.android.mynotes.model.Note
import ua.digijed2.android.mynotes.repositories.NoteRepository

class NoteViewModel(private val repo: NoteRepository) : ViewModel() {

    fun addNote(note: Note) = repo.addNote(note)
    fun getNoteById(oldNote: MutableLiveData<Note>, id: Long) = repo.getNoteById(oldNote, id)
    fun updateNote(note: Note) = repo.updateNote(note)

    class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NoteViewModel(repository) as T
        }
    }
}