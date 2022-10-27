package com.alaishat.ahmed.jetstore.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Created by Ahmed Al-Aishat on Sep/28/2022.
 * JetStore Project.
 */
data class Product(
    val id: Int,
    val title: String,
    val subtitle: String,
    val descriptionTitle: String = "",
    val description: String = "",
    val price: Int,
    val colors: List<ProductColor> = emptyList(),
    @DrawableRes val imageId: Int,
    val images: List<Int> = emptyList(),
) {
    fun toBasketProduct() = BasketProduct(id, title, price, 1, imageId)
}

data class ProductColor(
    @StringRes val colorName: Int,
    @ColorRes val colorId: Int,
)