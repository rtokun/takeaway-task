package com.example.takeawayrestaraunts.utils

import android.content.Context
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.example.takeawayrestaraunts.R

interface PreferenceManager {
    val isApiMocked: Boolean
}

class PreferenceManagerImpl(private val appContext: Context) : PreferenceManager {

    private val prefs = getDefaultSharedPreferences(appContext)

    override val isApiMocked: Boolean
        get() = prefs.getBoolean(appContext.getString(R.string.preference_key_use_mock_api), false)

}