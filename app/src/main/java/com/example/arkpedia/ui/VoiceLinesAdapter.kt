package com.example.arkpedia.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arkpedia.R

class VoiceLinesAdapter (private val listVoice: ArrayList<VoiceLines>) : RecyclerView.Adapter<VoiceLinesAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val keyLayout: TextView = itemView.findViewById(R.id.key)
        val descLayout: TextView = itemView.findViewById(R.id.desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_voice, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listVoice.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        if (listVoice[position].desc != ""){
            val (key, desc) = listVoice[position]
            holder.keyLayout.text = key
            holder.descLayout.text = desc
        }

    }
}