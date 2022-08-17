package com.alaishat.ahmed.mobostore.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alaishat.ahmed.mobostore.R

/**
 * Created by Ahmed Al-Aishat on Jul/31/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int? = null,
    @DrawableRes val drawableId: Int? = null,
    @DrawableRes val selectedDrawableId: Int? = null,
    val bottomBarVisible: Boolean = true,
) {
    object OnBoarding : Screen(route = "OnBoarding", bottomBarVisible = false)
    object Login : Screen(route = "Login", bottomBarVisible = false)

    object Home : Screen(
        "Home",
        drawableId = R.drawable.ic_home,
        selectedDrawableId = R.drawable.ic_home_selected
    )

    object Favorites :
        Screen(
            "Favorites",
            drawableId = R.drawable.ic_favorite,
            selectedDrawableId = R.drawable.ic_favorite_selected
        )

    object Profile :
        Screen(
            "Profile",
            drawableId = R.drawable.ic_profile,
            selectedDrawableId = R.drawable.ic_profile_selected
        )

    object Basket : Screen(
        "Basket",
        drawableId = R.drawable.ic_basket,
        selectedDrawableId = R.drawable.ic_basket_selected
    )

    object Checkout : Screen("Checkout")
    object SingleItem : Screen("SingleItem")
    object Search : Screen("Search")
    object OrderHistory : Screen("OrderHistory")
    object NoConnection : Screen("NoConnection")

    companion object {
        fun shouldBottomBarHidden(route: String?) = route == OnBoarding.route || route == Login.route
    }
}
