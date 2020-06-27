package com.example.takeawayrestaraunts.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.takeawayrestaraunts.networking.models.restaurant.Restaurant
import com.example.takeawayrestaraunts.repository.RestaurantsRepo
import com.example.takeawayrestaraunts.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

interface MainViewModel : RestaurantsAdapter.InteractionInterface {

    val restaurants: LiveData<List<Restaurant>>
    val error: LiveData<String>
    val isLoading: LiveData<Boolean>
    val isSwipeRefreshVisible: LiveData<Boolean>

    fun onSwipeRefreshCalled()
}

class MainViewModelImpl(private val restaurantsRepo: RestaurantsRepo) : MainViewModel, ViewModel() {

    override val error: MutableLiveData<String> = SingleLiveEvent()

    override val restaurants: LiveData<List<Restaurant>> = restaurantsRepo.restaurants
        .catch { throwable ->
            error.value = throwable.localizedMessage
        }.asLiveData()

    override val isLoading: LiveData<Boolean> = MutableLiveData<Boolean>()

    override val isSwipeRefreshVisible: MutableLiveData<Boolean> = MutableLiveData()

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            error.value = throwable.message
        }) {
            restaurantsRepo.getRestaurants(false)
        }
    }

    override fun onSwipeRefreshCalled() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            error.value = throwable.message
            isSwipeRefreshVisible.value = false
        }) {
            restaurantsRepo.getRestaurants(true)
            isSwipeRefreshVisible.value = false
        }
    }

    override fun onFavoriteChange(restaurantId: Int, isFavorite: Boolean) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            error.value = throwable.message
        }) {
            restaurantsRepo.setFavorite(isFavorite, restaurantId)
        }
    }
}