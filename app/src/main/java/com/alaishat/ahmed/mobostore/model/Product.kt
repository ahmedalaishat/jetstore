package com.alaishat.ahmed.mobostore.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Created by Ahmed Al-Aishat on Sep/28/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
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
)

data class ProductColor(
    @StringRes val colorName: Int,
    @ColorRes val colorId: Int,
)