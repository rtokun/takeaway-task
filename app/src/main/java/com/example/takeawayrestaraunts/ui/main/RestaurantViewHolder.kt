package com.example.takeawayrestaraunts.ui.main

import android.view.View
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.takeawayrestaraunts.R
import com.example.takeawayrestaraunts.networking.models.restaurant.Restaurant
import com.example.takeawayrestaraunts.utils.ImageManager
import kotlinx.android.synthetic.main.restaraunt_item.view.coverImage
import kotlinx.android.synthetic.main.restaraunt_item.view.favoriteCheckBox
import kotlinx.android.synthetic.main.restaraunt_item.view.isOpenTextView
import kotlinx.android.synthetic.main.restaraunt_item.view.minOrderTextView
import kotlinx.android.synthetic.main.restaraunt_item.view.nameTextView

class RestaurantViewHolder(
    rootView: View,
    private val imageManager: ImageManager
) : RecyclerView.ViewHolder(rootView) {

    private val context = rootView.context
    private val checkBox = rootView.favoriteCheckBox
    private val coverImage = rootView.coverImage
    private val nameTextView = rootView.nameTextView
    private val minOrderTextView = rootView.minOrderTextView
    private val isOpenTextView = rootView.isOpenTextView

    fun bind(
        restaurant: Restaurant,
        onCheckedChangeListener: CompoundButton.OnCheckedChangeListener
    ) {
        initImage(restaurant.coverImageUrl)
        initFavorite(restaurant.isFavorite, onCheckedChangeListener)
        initTexts(restaurant)
    }

    private fun initImage(coverImageUrl: String) {
        imageManager.loadCoverImageInto(coverImageUrl, coverImage)
    }

    private fun initTexts(restaurant: Restaurant) {
        nameTextView.text = restaurant.name
        minOrderTextView.text =
            context.getString(R.string.min_order_placeholder, restaurant.minimumOrder)

        // init is open text view color and text
        isOpenTextView.setTextColor(
            if (restaurant.isOpen) ContextCompat.getColor(
                context,
                android.R.color.holo_green_dark
            ) else ContextCompat.getColor(
                context,
                android.R.color.holo_red_dark
            )
        )
        isOpenTextView.text =
            if (restaurant.isOpen) context.getString(R.string.restaurant_open) else context.getString(
                R.string.restaurant_closed
            )
    }

    private fun initFavorite(
        isFavorite: Boolean,
        onCheckedChangeListener: CompoundButton.OnCheckedChangeListener
    ) {
        checkBox.isChecked = isFavorite
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener)
    }
}