package com.alaishat.ahmed.mobostore.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alaishat.ahmed.mobostore.ui.screens.*
import com.alaishat.ahmed.mobostore.ui.screens.basket.BasketScreen
import com.alaishat.ahmed.mobostore.ui.screens.home.HomeScreen
import com.alaishat.ahmed.mobostore.ui.screens.product.ProductScreen
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Ahmed Al-Aishat on Aug/21/2022.
 * Mobo Store Project.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    openDrawer: () -> Any,
    login: () -> Any,
    scope: CoroutineScope,
    startDestination: String,
    innerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(innerPadding),
    ) {
        composable(Screen.OnBoarding.route) { OnBoardingScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController, login) }
        composable(Screen.Home.route) { HomeScreen(navController, openDrawer, snackbarHostState) }
        composable(Screen.Favorites.route) { FavoritesScreen(navController) }
        composable(Screen.Profile.route) { ProfileScreen(navController) }
        composable(Screen.Basket.route) { BasketScreen(navController) }
        composable(Screen.Checkout.route) { CheckoutScreen(navController, scope) }
        composable(
            route = "${Screen.Product.route}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { ProductScreen(navController, snackbarHostState) }
        composable(Screen.Search.route) { SearchScreen(navController) }
        composable(Screen.OrderHistory.route) { HistoryScreen(navController) }
        composable(Screen.NoConnection.route) { NoConnectionScreen { } }
//        composable(Screen.Delivery.route) { BasketScreen(navController) }
//        composable(Screen.Settings.route) { ProfileScreen(navController) }
    }
}