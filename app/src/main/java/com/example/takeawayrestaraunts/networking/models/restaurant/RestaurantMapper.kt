package com.example.takeawayrestaraunts.networking.models.restaurant

fun RestaurantServerModel.toAppModel(isFavorite: Boolean): Restaurant {
    return Restaurant(coverImageUrl, id, minimumOrder, name, isOpen, isFavorite)
}