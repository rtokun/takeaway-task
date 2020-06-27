package com.example.takeawayrestaraunts.repository

import com.example.takeawayrestaraunts.db.daos.RestaurantsDao
import com.example.takeawayrestaraunts.networking.models.restaurant.Restaurant
import com.example.takeawayrestaraunts.networking.models.restaurant.toAppModel
import com.example.takeawayrestaraunts.networking.services.RestaurantsService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

interface RestaurantsRepo {

    fun getRestaurants(): Flow<List<Restaurant>>

    suspend fun setFavorite(
        isFavorite: Boolean,
        restaurantId: Int
    )


}

class RestaurantsRepoImpl(
    private val restaurantsService: RestaurantsService,
    private val db: RestaurantsDao
) : RestaurantsRepo {

    override fun getRestaurants() = flow {

        coroutineScope {
            launch {
                val fromServer = restaurantsService.getRestaurants()
                    .restaurantServerModels

                db.insertRestaurants(fromServer
                    .map {
                        it.toAppModel(false)
                    })
            }
        }

        emitAll(db.getAllRestaurants())

    }.flowOn(IO)

    override suspend fun setFavorite(isFavorite: Boolean, restaurantId: Int) {
        db.getRestaurantById(restaurantId)?.let {
            val updated = it.copy(isFavorite = isFavorite)
            db.update(updated)
        }
    }
}
