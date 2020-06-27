package com.example.takeawayrestaraunts.networking.models.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantServerModel(
    @SerializedName("coverImageUrl")
    val coverImageUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("minimumOrder")
    val minimumOrder: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("open")
    val isOpen: Boolean
)

data class Restaurant(
    val coverImageUrl: String,
    val id: Int,
    val minimumOrder: Int,
    val name: String,
    val isOpen: Boolean,
    val isFavorite: Boolean
)

