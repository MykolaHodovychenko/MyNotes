package ua.digijed2.android.mynotes.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ua.digijed2.android.mynotes.R
import ua.digijed2.android.mynotes.model.Note

class NotesAdapter(
    private val inflater: LayoutInflater,
    private val onClick: (Note) -> Unit
) :
    ListAdapter<Note, NotesAdapter.ViewHolder>(UserDiffCallback) {

    class ViewHolder(
        view: View,
        val onClick: (Note) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.title)
        private var note: Note? = null

        init {
            view.setOnClickListener {
                note?.let {
                    onClick(it)
                }
            }
        }

        fun bind(note: Note) {
            this.note = note
            this.title.text = note.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.notes_item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    object UserDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(
            oldItem: Note,
            newItem: Note
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: Note,
            newItem: Note
        ) = oldItem.title == newItem.title && oldItem.text == newItem.text
    }
}