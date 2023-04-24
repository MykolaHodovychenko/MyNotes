package ua.digijed2.android.mynotes.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.digijed2.android.mynotes.repositories.NoteRepository

class MainViewModel(private val repo: NoteRepository) : ViewModel() {

    val notes = repo.notes

    class MainViewModelFactory(private val repo: NoteRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repo) as T
        }
    }
}