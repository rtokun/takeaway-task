package com.example.takeawayrestaraunts.networking.models.restaurant

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.takeawayrestaraunts.db.DBConstants.RESTAURANTS_TABLE
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

@Entity(tableName = RESTAURANTS_TABLE)
data class Restaurant(
    @PrimaryKey
    val id: Int,
    val coverImageUrl: String,
    val minimumOrder: Int,
    val name: String,
    val isOpen: Boolean,
    val isFavorite: Boolean
)

