package com.example.arkpedia.ui.adapter

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
import com.example.arkpedia.ui.detail.DetailOperator

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
                    intent.putExtra("name", intentData.name)
                    intent.putExtra("className", intentData.className[0])
                    intent.putExtra("rarity", intentData.rarity)
                    intent.putExtra("image", intentData.art[0].link)

                    // Operators File
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

                    // Voice Lines
                    intent.putExtra("appointed_as_assistant", intentData.voicelines.appointedAsAssistant)
                    intent.putExtra("talk_1", intentData.voicelines.talk1)
                    intent.putExtra("talk_2", intentData.voicelines.talk2)
                    intent.putExtra("talk_3", intentData.voicelines.talk3)
                    intent.putExtra("talk_after_promotion_1", intentData.voicelines.talkAfterPromotion1)
                    intent.putExtra("talk_after_promotion_2", intentData.voicelines.talkAfterPromotion2)
                    intent.putExtra("talk_after_trust_increase_1", intentData.voicelines.talkAfterTrustIncrease1)
                    intent.putExtra("talk_after_trust_increase_2", intentData.voicelines.talkAfterTrustIncrease2)
                    intent.putExtra("talk_after_trust_increase_3", intentData.voicelines.talkAfterTrustIncrease3)
                    intent.putExtra("idle", intentData.voicelines.idle)
                    intent.putExtra("onboard", intentData.voicelines.onboard)
                    intent.putExtra("watching_battle_record", intentData.voicelines.watchingBattleRecord)
                    intent.putExtra("promotion_1", intentData.voicelines.promotion1)
                    intent.putExtra("promotion_2", intentData.voicelines.promotion2)
                    intent.putExtra("added_to_squad", intentData.voicelines.addedToSquad)
                    intent.putExtra("appointed_as_squad_leader", intentData.voicelines.appointedAsSquadLeader)
                    intent.putExtra("depart", intentData.voicelines.depart)
                    intent.putExtra("begin_operation", intentData.voicelines.beginOperation)
                    intent.putExtra("selecting_operator_1", intentData.voicelines.selectingOperator1)
                    intent.putExtra("selecting_operator_2", intentData.voicelines.selectingOperator2)
                    intent.putExtra("deployment_1", intentData.voicelines.deployment1)
                    intent.putExtra("deployment_2", intentData.voicelines.deployment2)
                    intent.putExtra("in_battle_1", intentData.voicelines.inBattle1)
                    intent.putExtra("in_battle_2", intentData.voicelines.inBattle2)
                    intent.putExtra("in_battle_3", intentData.voicelines.inBattle3)
                    intent.putExtra("in_battle_4", intentData.voicelines.inBattle4)
                    intent.putExtra("4-star_result", intentData.voicelines.fourStarResult)
                    intent.putExtra("3-star_result", intentData.voicelines.threeStarResult)
                    intent.putExtra("sub_3-star_result", intentData.voicelines.subThreeStarResult)
                    intent.putExtra("operation_failure", intentData.voicelines.operationFailure)
                    intent.putExtra("assigned_to_facility", intentData.voicelines.assignedToFacility)
                    intent.putExtra("tap", intentData.voicelines.tap)
                    intent.putExtra("trust_tap", intentData.voicelines.trustTap)
                    intent.putExtra("title", intentData.voicelines.title)
                    intent.putExtra("greeting", intentData.voicelines.greeting)

                    // Data Stat
                    intent.putExtra("base_hp", intentData.statistics.base.hp)
                    intent.putExtra("base_atk", intentData.statistics.base.atk)
                    intent.putExtra("base_def", intentData.statistics.base.def)
                    intent.putExtra("base_resist", intentData.statistics.base.resist)
                    intent.putExtra("base_deploy", intentData.statistics.base.deploy)
                    intent.putExtra("base_cost", intentData.statistics.base.cost)
                    intent.putExtra("base_interval", intentData.statistics.base.interval)
                    intent.putExtra("base_block", intentData.statistics.base.block)

                    intent.putExtra("e0max_hp", intentData.statistics.e0max.hp)
                    intent.putExtra("e0max_atk", intentData.statistics.e0max.atk)
                    intent.putExtra("e0max_def", intentData.statistics.e0max.def)
                    intent.putExtra("e0max_block", intentData.statistics.e0max.block)

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