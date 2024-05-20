package com.example.arkpedia.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.arkpedia.R

class RarityAdapter(private val imageResId: Int, private val rarity: Int) :
    RecyclerView.Adapter<RarityAdapter.RarityViewHolder>() {

    class RarityViewHolder (view: View): RecyclerView.ViewHolder(view){
        private val childImageView: ImageView = itemView.findViewById(R.id.rarity)

        fun bind(imageResId: Int) {
            childImageView.setImageResource(imageResId)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RarityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rarity_star_items, parent, false)
        return RarityViewHolder(view)
    }

    override fun onBindViewHolder(holder: RarityViewHolder, position: Int) {
        holder.bind(imageResId)
    }

    override fun getItemCount(): Int {
        return rarity
    }


}
