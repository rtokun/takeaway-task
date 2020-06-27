package com.example.takeawayrestaraunts.networking.services

import com.example.takeawayrestaraunts.networking.models.restaurant.RestaurantsResponse
import retrofit2.http.GET

interface RestaurantsService {

    @GET("/gilgoldzweig/SampleTest/db")
    suspend fun getRestaurants(): RestaurantsResponse

}