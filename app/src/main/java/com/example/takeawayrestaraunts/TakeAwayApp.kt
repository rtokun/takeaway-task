package com.example.takeawayrestaraunts

import android.app.Application
import com.example.takeawayrestaraunts.di.modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TakeAwayApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            // declare used Android context
            androidContext(this@TakeAwayApp)
            // declare modules
            modules(modules)
        }
    }

}