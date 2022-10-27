package com.alaishat.ahmed.jetstore.model

import androidx.annotation.DrawableRes

/**
 * Created by Ahmed Al-Aishat on Sep/30/2022.
 * JetStore Project.
 */
data class BasketProduct(
    val id: Int,
    val title: String,
//    val subtitle: String,
//    val descriptionTitle: String = "",
//    val description: String = "",
    val price: Int,
    val count: Int,
//    val colors: List<ProductColor> = emptyList(),
    @DrawableRes val imageId: Int,
//    val images: List<Int> = emptyList(),
)
