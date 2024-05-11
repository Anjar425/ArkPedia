package com.example.arkpedia.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OperatorsData(
    val name : String?,
    val image : String?,
    val lore : Character?
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