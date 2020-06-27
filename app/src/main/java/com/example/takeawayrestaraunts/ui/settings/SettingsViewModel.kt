package com.example.takeawayrestaraunts.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takeawayrestaraunts.repository.RestaurantsRepo
import com.example.takeawayrestaraunts.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

interface SettingsViewModel {
    val error: LiveData<String>

    fun onMockApiCheckChanged()
}

class SettingsViewModelImpl(private val restaurantsRepo: RestaurantsRepo) :
    SettingsViewModel, ViewModel() {

    override val error: MutableLiveData<String> = SingleLiveEvent()

    override fun onMockApiCheckChanged() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            error.value = throwable.message
        }) {
            restaurantsRepo.clearCache()
            restaurantsRepo.getRestaurants(true)
        }
    }
}