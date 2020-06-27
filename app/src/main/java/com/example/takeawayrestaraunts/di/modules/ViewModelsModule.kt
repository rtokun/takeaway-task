package com.example.takeawayrestaraunts.di.modules

import com.example.takeawayrestaraunts.ui.main.MainViewModelImpl
import com.example.takeawayrestaraunts.ui.settings.SettingsViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel {
        MainViewModelImpl(
            restaurantsRepo = get()
        )
    }

    viewModel {
        SettingsViewModelImpl(
            restaurantsRepo = get()
        )
    }

}