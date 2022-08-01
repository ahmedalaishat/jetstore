package com.alaishat.ahmed.mobostore.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Created by Ahmed Al-Aishat on Jul/31/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int? = null,
    @DrawableRes val drawableId: Int? = null
) {
    object OnBoarding : Screen("OnBoarding")
    object Login : Screen("Login")
    object Home : Screen("Home")
}
