package com.example.takeawayrestaraunts.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

interface ImageManager {
    fun loadCoverImageInto(imageUrl: String, container: ImageView)
}

class ImageManagerImpl : ImageManager {

    override fun loadCoverImageInto(imageUrl: String, container: ImageView) {
        Glide.with(container.context)
            .load(imageUrl)
            .error(android.R.color.darker_gray)
            .into(container)
    }

}