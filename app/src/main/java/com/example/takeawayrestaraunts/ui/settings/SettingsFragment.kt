package com.example.takeawayrestaraunts.ui.settings

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.takeawayrestaraunts.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : PreferenceFragmentCompat() {

    private val viewModel: SettingsViewModel by viewModel<SettingsViewModelImpl>()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        findPreference<Preference>(getString(R.string.preference_key_use_mock_api))
            ?.let {
                it.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                    viewModel.onMockApiCheckChanged()
                    return@OnPreferenceClickListener true
                }
            }
    }
}