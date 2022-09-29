package com.alaishat.ahmed.mobostore.ui.navigation

import androidx.annotation.StringRes
import com.alaishat.ahmed.mobostore.R

/**
 * Created by Ahmed Al-Aishat on Aug/01/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
sealed class HomeTab(
    val name: String,
    @StringRes val nameId: Int
) {
    object Wearable : HomeTab("Wearable", R.string.wearable)
    object Laptops : HomeTab("Laptops", R.string.laptops)
    object Phones : HomeTab("Phones", R.string.phones)
    object Drones : HomeTab("Drones", R.string.buds)
}