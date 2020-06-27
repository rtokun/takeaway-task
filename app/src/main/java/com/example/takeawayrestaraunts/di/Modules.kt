package com.example.takeawayrestaraunts.di

import com.example.takeawayrestaraunts.di.modules.adaptersModule
import com.example.takeawayrestaraunts.di.modules.dbModule
import com.example.takeawayrestaraunts.di.modules.networkingModule
import com.example.takeawayrestaraunts.di.modules.repositoriesModule
import com.example.takeawayrestaraunts.di.modules.utilsModule
import com.example.takeawayrestaraunts.di.modules.viewModelsModule

val modules = listOf(
    networkingModule,
    viewModelsModule,
    repositoriesModule,
    adaptersModule,
    utilsModule,
    dbModule
)