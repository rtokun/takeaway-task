package com.example.takeawayrestaraunts.di.modules

import com.example.takeawayrestaraunts.utils.ImageManager
import com.example.takeawayrestaraunts.utils.ImageManagerImpl
import org.koin.dsl.module

val utilsModule = module {

    factory<ImageManager> {
        ImageManagerImpl()
    }
}