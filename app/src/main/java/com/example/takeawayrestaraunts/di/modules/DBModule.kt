@file:Suppress("RemoveExplicitTypeArguments")

package com.example.takeawayrestaraunts.di.modules

import com.example.takeawayrestaraunts.db.TakeAwayDb
import com.example.takeawayrestaraunts.db.daos.RestaurantsDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {

    single<TakeAwayDb> {
        TakeAwayDb.create(androidContext(), false)
    }

    factory<RestaurantsDao> {
        get<TakeAwayDb>().restaurantsDao()
    }
}
