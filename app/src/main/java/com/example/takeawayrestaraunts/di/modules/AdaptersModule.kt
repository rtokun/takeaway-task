package com.example.takeawayrestaraunts.di.modules

import com.example.takeawayrestaraunts.ui.main.RestaurantDiffUtilCallback
import com.example.takeawayrestaraunts.ui.main.RestaurantsAdapter
import org.koin.dsl.module

val adaptersModule = module {

    factory {
        RestaurantDiffUtilCallback()
    }

    factory { (interactionInterface: RestaurantsAdapter.InteractionInterface) ->
        RestaurantsAdapter(
            diffUtilCallBack = get(),
            imageManager = get(),
            interactionInterface = interactionInterface
        )
    }
}