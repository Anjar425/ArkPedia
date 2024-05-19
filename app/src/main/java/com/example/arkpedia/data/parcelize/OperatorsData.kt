package com.example.arkpedia.data.parcelize

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OperatorsData(
    val name : String?,
    val image : String?,
    val profile : String?,
    val className: String?,
    val rarity: Int?,
    val lore : Character?,
    val voicelines : ArrayList<VoiceLines>,
    val base: Base?,
    val e0max: E0max?
) : Parcelable


@Parcelize
data class Base(
    val hp: String?,
    val atk: String?,
    val def: String?,
    val resist: String?,
    val deploy: String?,
    val cost: String?,
    val interval: String?,
    val block: String?
) : Parcelable

@Parcelize
data class E0max(
    val hp: String?,
    val atk: String?,
    val def: String?,
    val block: String?
) : Parcelable
@Parcelize
data class Character(
    val gender: String?,
    val placeOfBirth: String?,
    val birthday: String?,
    val race: String?,
    val height: String?,
    val combatExperience: String?,
    val infectionStatus: String?,
    val physicalStrength: String?,
    val mobility: String?,
    val physiologicalEndurance: String?,
    val tacticalPlanning: String?,
    val combatSkill: String?,
    val originiumAdaptability: String?
) : Parcelable

@Parcelize
data class VoiceLines (
    val key: String?,
    val desc: String?,
) : Parcelable
