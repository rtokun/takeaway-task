package com.example.takeawayrestaraunts.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.example.takeawayrestaraunts.networking.models.restaurant.Restaurant

class RestaurantDiffUtilCallback : DiffUtil.ItemCallback<Restaurant>() {

    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem == newItem
    }

}