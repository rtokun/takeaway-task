package com.example.takeawayrestaraunts.di.modules

import com.example.takeawayrestaraunts.repository.RestaurantsRepo
import com.example.takeawayrestaraunts.repository.RestaurantsRepoImpl
import org.koin.dsl.module

val repositoriesModule = module {

    single<RestaurantsRepo> {
        RestaurantsRepoImpl(
            restaurantsService = get(),
            db = get()
        )
    }

}