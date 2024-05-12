package com.example.arkpedia.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.arkpedia.R
import com.example.arkpedia.data.response.ArtItem
import com.example.arkpedia.data.response.UserResponse

class UserAdapter (
    private val context: Context,
    private val data: ArrayList<UserResponse>
): RecyclerView.Adapter<UserAdapter.UserViewHolder>()    {
    class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView? = view.findViewById(R.id.name)
        val className : TextView? = view.findViewById(R.id.className)
        val image: ImageView = view.findViewById(R.id.img_item_photo)
        val classPhoto : ImageView = view.findViewById(R.id.classPhoto)
        val tags : TextView? = view.findViewById(R.id.tags)
        private val childRecyclerView: RecyclerView = itemView.findViewById(R.id.rarity_view)

        fun bind(rarity: Int) {
            val layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            childRecyclerView.layoutManager = layoutManager
            childRecyclerView.adapter = RarityAdapter(R.drawable.rarity_star, rarity)
        }
        val cvUser: CardView? = view.findViewById(R.id.card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.row_items, parent, false)
        return  UserViewHolder(itemView)
    }

    @SuppressLint("DiscouragedApi")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        try {
            if (data[position].name.isNotEmpty() && data[position].className.isNotEmpty() && data[position].tags.isNotEmpty() && position < data.size) {
                val name = data[position].name
                val className = data[position].className[0]
                val rarity: Int = data[position].rarity
                val image: List<ArtItem> = data[position].art

                holder.name?.text = name

                holder.className?.text = className
                val resourceId = context.resources.getIdentifier(
                    className.lowercase(), "drawable", context.packageName
                )
                holder.classPhoto.setImageResource(resourceId)

                val tags: StringBuilder = StringBuilder()
                data[position].tags.forEachIndexed { index, tag ->
                    tags.append(tag)
                    if (index < data[position].tags.size - 1) {
                        tags.append(", ") // Menambahkan koma jika bukan tag terakhir
                    }
                }
                holder.tags?.text = tags.toString()

                Glide.with(context)
                    .load(image[0].link)
                    .into(holder.image)

                holder.bind(rarity)

                val intentData = data[position]
                holder.cvUser?.setOnClickListener {
                    val intent = Intent(context, DetailOperator::class.java)
                    intent.putExtra("name", name)
                    intent.putExtra("className", className)
                    intent.putExtra("tags", tags.toString())
                    intent.putExtra("image", image[0].link)
                    intent.putExtra("profile", intentData.biography)
                    intent.putExtra("gender", intentData.lore.gender)
                    intent.putExtra("place_of_birth", intentData.lore.placeOfBirth)
                    intent.putExtra("birthday", intentData.lore.birthday)
                    intent.putExtra("race", intentData.lore.race)
                    intent.putExtra("height", intentData.lore.height)
                    intent.putExtra("combat_experience", intentData.lore.combatExperience)
                    intent.putExtra("infection_status", intentData.lore.infectionStatus)
                    intent.putExtra("physical_strength", intentData.lore.physicalStrength)
                    intent.putExtra("mobility", intentData.lore.mobility)
                    intent.putExtra("physiological_endurance", intentData.lore.physiologicalEndurance)
                    intent.putExtra("tactical_planning", intentData.lore.tacticalPlanning)
                    intent.putExtra("combat_skill", intentData.lore.combatSkill)
                    intent.putExtra("originium_adaptability", intentData.lore.originiumAdaptability)
                    context.startActivity(intent)
                }
            }
        } catch (e: IndexOutOfBoundsException) {
            // Tangani jika terjadi IndexOutOfBoundsException
            e.printStackTrace() // Menampilkan jejak eksepsi untuk debug
        } catch (e: Exception) {
            // Tangani exception lainnya
            e.printStackTrace() // Menampilkan jejak eksepsi untuk debug
        }
    }


    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: ArrayList<UserResponse>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}