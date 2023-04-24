package ua.digijed2.android.mynotes.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ua.digijed2.android.mynotes.model.Note
import ua.digijed2.android.mynotes.room.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    val notes: LiveData<List<Note>> = noteDao.getAllNotes()

    fun addNote(note: Note) = Thread { noteDao.insert(note) }.start()

    fun getNoteById(oldNote: MutableLiveData<Note>, id: Long) {
        Thread {
            noteDao.getNoteById(id).let {
                oldNote.postValue(it)
            }
        }.start()
    }

    fun updateNote(note: Note) = Thread { noteDao.updateNote(note) }.start()
}