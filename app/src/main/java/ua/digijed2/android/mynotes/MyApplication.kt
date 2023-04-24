package ua.digijed2.android.mynotes

import android.app.Application
import ua.digijed2.android.mynotes.repositories.NoteRepository
import ua.digijed2.android.mynotes.room.NotesDatabase

class MyApplication : Application() {

    private val database by lazy {
        NotesDatabase.getDatabase(this)
    }

    val repository by lazy {
        NoteRepository(database.noteDao())
    }
}