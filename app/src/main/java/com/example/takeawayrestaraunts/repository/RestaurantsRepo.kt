package com.example.takeawayrestaraunts.repository

import com.example.takeawayrestaraunts.networking.models.restaurant.Restaurant
import com.example.takeawayrestaraunts.networking.models.restaurant.toAppModel
import com.example.takeawayrestaraunts.networking.services.RestaurantsService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface RestaurantsRepo {
    fun getRestaurants(): Flow<List<Restaurant>>
}

class RestaurantsRepoImpl(val restaurantsService: RestaurantsService) : RestaurantsRepo {

    override fun getRestaurants() = flow<List<Restaurant>> {
        val fromServer = restaurantsService.getRestaurants()
            .restaurantServerModels

        emit(fromServer
            .map {
                it.toAppModel(false)
            })
    }.flowOn(IO)
}
