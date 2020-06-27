package com.example.takeawayrestaraunts.di.modules

import com.example.takeawayrestaraunts.BuildConfig.BASE_URL
import com.example.takeawayrestaraunts.networking.mocking.MockInterceptor
import com.example.takeawayrestaraunts.networking.services.RestaurantsService
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkingModule = module {

    factory<RestaurantsService> {
        val retrofit: Retrofit = get()
        retrofit.create(RestaurantsService::class.java)
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<MockInterceptor>())
            .build()
    }

    factory {
        MockInterceptor(
            preferenceManager = get(),
            appContext = androidContext()
        )
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}