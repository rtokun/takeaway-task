package com.example.takeawayrestaraunts.di.modules

import com.example.takeawayrestaraunts.utils.ImageManager
import com.example.takeawayrestaraunts.utils.ImageManagerImpl
import com.example.takeawayrestaraunts.utils.PreferenceManager
import com.example.takeawayrestaraunts.utils.PreferenceManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val utilsModule = module {

    factory<ImageManager> {
        ImageManagerImpl()
    }

    factory<PreferenceManager> {
        PreferenceManagerImpl(
            appContext = androidContext()
        )
    }
}