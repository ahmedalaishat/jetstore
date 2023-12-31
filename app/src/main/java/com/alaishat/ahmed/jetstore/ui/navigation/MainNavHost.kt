package com.alaishat.ahmed.jetstore.ui.navigation

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
import com.alaishat.ahmed.jetstore.ui.screens.*
import com.alaishat.ahmed.jetstore.ui.screens.basket.BasketScreen
import com.alaishat.ahmed.jetstore.ui.screens.home.HomeScreen
import com.alaishat.ahmed.jetstore.ui.screens.login.LoginScreen
import com.alaishat.ahmed.jetstore.ui.screens.product.ProductScreen
import com.alaishat.ahmed.jetstore.ui.screens.profile.ProfileScreen
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Ahmed Al-Aishat on Aug/21/2022.
 * JetStore Project.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    openDrawer: () -> Any,
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
        composable(Screen.Login.route) { LoginScreen() }
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