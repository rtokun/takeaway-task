package com.example.takeawayrestaraunts.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.takeawayrestaraunts.networking.models.restaurant.Restaurant
import com.example.takeawayrestaraunts.repository.RestaurantsRepo
import com.example.takeawayrestaraunts.utils.SingleLiveEvent

interface MainViewModel : RestaurantsAdapter.InteractionInterface {
    val restaurants: LiveData<List<Restaurant>>
    val error: LiveData<String>
    val isLoading: LiveData<Boolean>
}

class MainViewModelImpl(private val restaurantsRepo: RestaurantsRepo) : MainViewModel, ViewModel() {

    override val restaurants: LiveData<List<Restaurant>> = restaurantsRepo
        .getRestaurants()
        .asLiveData()

    override val error: LiveData<String> = SingleLiveEvent()

    override val isLoading: LiveData<Boolean> = MutableLiveData<Boolean>()

    override fun onFavoriteChange(restaurantId: Int, isFavorite: Boolean) {

    }
}