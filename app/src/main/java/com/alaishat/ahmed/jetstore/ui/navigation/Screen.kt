package com.alaishat.ahmed.jetstore.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alaishat.ahmed.jetstore.R

/**
 * Created by Ahmed Al-Aishat on Jul/31/2022.
 * JetStore Project.
 */
sealed class Screen(
    val route: String,
    val name: String = route,
    @StringRes val resourceId: Int? = null,
    @DrawableRes val drawableId: Int? = null,
    @DrawableRes val selectedDrawableId: Int? = drawableId,
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
        name = "My orders",
        drawableId = R.drawable.ic_basket,
        selectedDrawableId = R.drawable.ic_basket_selected
    )

    object Delivery : Screen(
        route = "Delivery",
        drawableId = R.drawable.ic_bag,
    )

    object Settings : Screen(
        route = "Settings",
        drawableId = R.drawable.ic_setting,
    )

    object Checkout : Screen("Checkout")
    object Product : Screen("Product")
    object Search : Screen("Search")
    object OrderHistory : Screen("OrderHistory")
    object NoConnection : Screen("NoConnection")

    fun getDrawableId(selected: Boolean): Int? {
        return if (selected) selectedDrawableId else drawableId
    }

    companion object {
        private fun loginScreens() = listOf(
            OnBoarding,
            Login,
        )

        fun bottomBarScreens() = listOf(
            Home,
            Favorites,
            Profile,
            Basket,
        )

        private fun notImplementedScreens() = listOf(
            Delivery,
            Settings,
        )

        fun drawerMenuScreens() = listOf(
            Profile,
            Basket,
            Favorites,
            Delivery,
            Settings,
        )

        fun isLoginScreen(route: String?) = loginScreens().map(Screen::route).contains(route)

        fun isBottomBarScreen(route: String?) = bottomBarScreens().map(Screen::route).contains(route)

        fun isImplemented(route: String?) = !notImplementedScreens().map(Screen::route).contains(route)

        fun getStartDestination(isLoggedIn: Boolean) = if (isLoggedIn) Home else OnBoarding
    }
}
