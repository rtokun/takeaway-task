package com.example.takeawayrestaraunts.networking.models.restaurant

fun RestaurantServerModel.toAppModel(isFavorite: Boolean): Restaurant {
    return Restaurant(id, coverImageUrl, minimumOrder, name, isOpen, isFavorite)
}