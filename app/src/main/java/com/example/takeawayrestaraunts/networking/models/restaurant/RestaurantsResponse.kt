package com.example.takeawayrestaraunts.networking.models.restaurant


import com.example.takeawayrestaraunts.networking.models.restaurant.RestaurantServerModel
import com.google.gson.annotations.SerializedName

data class RestaurantsResponse(
    @SerializedName("restaurants")
    val restaurantServerModels: List<RestaurantServerModel>
)