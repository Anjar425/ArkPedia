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
    val tags: List<String>

)
