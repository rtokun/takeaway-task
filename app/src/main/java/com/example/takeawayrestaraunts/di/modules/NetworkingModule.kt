package com.example.takeawayrestaraunts.di.modules

import com.example.takeawayrestaraunts.BuildConfig.BASE_URL
import com.example.takeawayrestaraunts.networking.services.RestaurantsService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkingModule = module {

    factory<RestaurantsService> {
        val retrofit: Retrofit = get()
        retrofit.create(RestaurantsService::class.java)
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}