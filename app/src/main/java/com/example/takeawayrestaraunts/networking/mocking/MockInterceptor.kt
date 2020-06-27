package com.example.takeawayrestaraunts.networking.mocking

import android.content.Context
import com.example.takeawayrestaraunts.utils.PreferenceManager
import com.example.takeawayrestaraunts.utils.loadJSONFromAssets
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

class MockInterceptor(
    private val preferenceManager: PreferenceManager,
    private val appContext: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (preferenceManager.isApiMocked) {
            getMockResponse(chain)
        } else {
            chain.proceed(chain.request())
        }
    }

    private fun getMockResponse(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()
        val responseString = when {
            uri.endsWith("gilgoldzweig/SampleTest/db") -> appContext.loadJSONFromAssets("mocked_restaurants.json")
            else -> ""
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                ResponseBody.create(
                    MediaType.parse("application/json"),
                    responseString.toByteArray()
                )
            )
            .addHeader("content-type", "application/json")
            .build()
    }

}