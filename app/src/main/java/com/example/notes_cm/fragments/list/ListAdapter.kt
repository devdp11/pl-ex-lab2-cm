package com.example.notes_cm.fragments.list

import android.app.DirectAction
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_cm.R
import com.example.notes_cm.data.entities.Note

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var notesList = emptyList<Note>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = notesList[position]
        holder.itemView.findViewById<TextView>(R.id.note_txt).text = currentItem.note
        holder.itemView.findViewById<TextView>(R.id.date_txt).text = currentItem.date

        if(position%2 == 0)
            holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout).setBackgroundColor(Color.parseColor("#b5b5b5"))
        else
            holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout).setBackgroundColor(Color.parseColor("#50d46d"))

        holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)

        }
    }

    fun setData(note: List<Note>){
        this.notesList = note
        notifyDataSetChanged()
    }
}