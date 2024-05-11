package com.example.arkpedia.data.response

import com.google.gson.annotations.SerializedName

data class ArtItem(
    @field:SerializedName("link")
    val link: String
)

data class UserResponse(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("biography")
    val biography: String,

    @field:SerializedName("rarity")
    val rarity: Int,

    @field:SerializedName("art")
    val art: List<ArtItem>,

    @field:SerializedName("class")
    val className: List<String>,

    @field:SerializedName("tags")
    val tags: List<String>,

    @SerializedName("lore")
    val lore: CharacterData
)

data class CharacterData(
    @SerializedName("gender")
    val gender: String,
    @SerializedName("place_of_birth")
    val placeOfBirth: String,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("race")
    val race: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("combat_experience")
    val combatExperience: String,
    @SerializedName("infection_status")
    val infectionStatus: String,
    @SerializedName("physical_strength")
    val physicalStrength: String,
    @SerializedName("mobility")
    val mobility: String,
    @SerializedName("physiological_endurance")
    val physiologicalEndurance: String,
    @SerializedName("tactical_planning")
    val tacticalPlanning: String,
    @SerializedName("combat_skill")
    val combatSkill: String,
    @SerializedName("originium_adaptability")
    val originiumAdaptability: String
)
