package com.example.arkpedia.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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
    val lore: CharacterData,

    @SerializedName("voicelines")
    val voicelines: VoiceLines
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

data class VoiceLines (
    @SerializedName("appointed_as_assistant")
    val appointedAsAssistant: String,

    @SerializedName("talk_1")
    val talk1: String,

    @SerializedName("talk_2")
    val talk2: String,

    @SerializedName("talk_3")
    val talk3: String,

    @SerializedName("talk_after_promotion_1")
    val talkAfterPromotion1: String,

    @SerializedName("talk_after_promotion_2")
    val talkAfterPromotion2: String,

    @SerializedName("talk_after_trust_increase_1")
    val talkAfterTrustIncrease1: String,

    @SerializedName("talk_after_trust_increase_2")
    val talkAfterTrustIncrease2: String,

    @SerializedName("talk_after_trust_increase_3")
    val talkAfterTrustIncrease3: String,

    @SerializedName("idle")
    val idle: String,

    @SerializedName("onboard")
    val onboard: String,

    @SerializedName("watching_battle_record")
    val watchingBattleRecord: String,

    @SerializedName("promotion_1")
    val promotion1: String,

    @SerializedName("promotion_2")
    val promotion2: String,

    @SerializedName("added_to_squad")
    val addedToSquad: String,

    @SerializedName("appointed_as_squad_leader")
    val appointedAsSquadLeader: String,

    @SerializedName("depart")
    val depart: String,

    @SerializedName("begin_operation")
    val beginOperation: String,

    @SerializedName("selecting_operator_1")
    val selectingOperator1: String,

    @SerializedName("selecting_operator_2")
    val selectingOperator2: String,

    @SerializedName("deployment_1")
    val deployment1: String,

    @SerializedName("deployment_2")
    val deployment2: String,

    @SerializedName("in_battle_1")
    val inBattle1: String,

    @SerializedName("in_battle_2")
    val inBattle2: String,

    @SerializedName("in_battle_3")
    val inBattle3: String,

    @SerializedName("in_battle_4")
    val inBattle4: String,

    @SerializedName("4-star_result")
    val fourStarResult: String,

    @SerializedName("3-star_result")
    val threeStarResult: String,

    @SerializedName("sub_3-star_result")
    val subThreeStarResult: String,

    @SerializedName("operation_failure")
    val operationFailure: String,

    @SerializedName("assigned_to_facility")
    val assignedToFacility: String,

    @SerializedName("tap")
    val tap: String,

    @SerializedName("trust_tap")
    val trustTap: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("greeting")
    val greeting: String
)