package com.example.takeawayrestaraunts.repository

import com.example.takeawayrestaraunts.db.daos.RestaurantsDao
import com.example.takeawayrestaraunts.networking.models.restaurant.Restaurant
import com.example.takeawayrestaraunts.networking.models.restaurant.toAppModel
import com.example.takeawayrestaraunts.networking.services.RestaurantsService
import kotlinx.coroutines.flow.Flow

interface RestaurantsRepo {

    val restaurants: Flow<List<Restaurant>>

    suspend fun getRestaurants(forceRefresh: Boolean)

    suspend fun setFavorite(
        isFavorite: Boolean,
        restaurantId: Int
    )

    suspend fun clearCache()
}

class RestaurantsRepoImpl(
    private val restaurantsService: RestaurantsService,
    private val db: RestaurantsDao
) : RestaurantsRepo {

    override val restaurants = db.getAllRestaurants()

    override suspend fun getRestaurants(forceRefresh: Boolean) {

        if (!forceRefresh && db.getRestaurantsCount() > 0) {
            // we have cached data, just use it
            return
        }

        val fromServer = restaurantsService.getRestaurants().restaurantServerModels
            .map { it.toAppModel(false) }
        db.insertRestaurants(fromServer)
    }

    override suspend fun setFavorite(isFavorite: Boolean, restaurantId: Int) {
        db.getRestaurantById(restaurantId)?.let {
            val updated = it.copy(isFavorite = isFavorite)
            db.update(updated)
        }
    }

    override suspend fun clearCache() {
        db.clear()
    }
}
