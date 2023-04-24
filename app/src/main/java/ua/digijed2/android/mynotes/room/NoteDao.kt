package ua.digijed2.android.mynotes.room

import androidx.lifecycle.LiveData
import androidx.room.*
import ua.digijed2.android.mynotes.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY updated DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Long): Note

    @Update
    fun updateNote(note: Note)
}