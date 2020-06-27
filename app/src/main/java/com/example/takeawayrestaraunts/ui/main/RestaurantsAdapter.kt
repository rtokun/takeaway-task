package com.example.takeawayrestaraunts.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.takeawayrestaraunts.R
import com.example.takeawayrestaraunts.networking.models.restaurant.Restaurant
import com.example.takeawayrestaraunts.utils.ImageManager
import org.koin.core.parameter.DefinitionParameters
import org.koin.core.parameter.parametersOf

class RestaurantsAdapter(
    diffUtilCallBack: RestaurantDiffUtilCallback,
    private val imageManager: ImageManager,
    private val interactionInterface: InteractionInterface
) :
    RecyclerView.Adapter<RestaurantViewHolder>() {

    private val asyncListDiffer = AsyncListDiffer<Restaurant>(this, diffUtilCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaraunt_item, parent, false)
        return RestaurantViewHolder(rootView, imageManager)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = asyncListDiffer.currentList[position]
        holder.bind(
            restaurant,
            CompoundButton.OnCheckedChangeListener { _, isChecked ->
                interactionInterface.onFavoriteChange(restaurant.id, isChecked)
            })
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    fun submitItems(newItems: List<Restaurant>) {
        asyncListDiffer.submitList(newItems)
    }

    interface InteractionInterface {
        fun onFavoriteChange(restaurantId: Int, isFavorite: Boolean)
    }

    companion object {
        fun getInjectorDefinitionParameters(
            interactionInterface: InteractionInterface
        ): DefinitionParameters {
            return parametersOf(
                interactionInterface
            )
        }
    }
}