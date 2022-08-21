package com.alaishat.ahmed.mobostore.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alaishat.ahmed.mobostore.ui.screens.*
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Ahmed Al-Aishat on Aug/21/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    modalBottomSheetState: ModalBottomSheetState,
    openDrawer: () -> Any,
    scope: CoroutineScope,
    startDestination: String,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = androidx.compose.ui.Modifier.padding(innerPadding),
    ) {
        composable(Screen.OnBoarding.route) { OnBoardingScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Home.route) { HomeScreen(navController, openDrawer) }
        composable(Screen.Favorites.route) { FavoritesScreen(navController) }
        composable(Screen.Profile.route) { ProfileScreen(navController) }
        composable(Screen.Basket.route) { BasketScreen(navController) }
        composable(Screen.Checkout.route) { CheckoutScreen(navController, scope, modalBottomSheetState) }
        composable(Screen.SingleItem.route) { SingleItemScreen(navController) }
        composable(Screen.Search.route) { SearchScreen(navController) }
        composable(Screen.OrderHistory.route) { HistoryScreen(navController) }
        composable(Screen.NoConnection.route) { NoConnectionScreen(navController) }
        composable(Screen.Delivery.route) { BasketScreen(navController) }
        composable(Screen.Settings.route) { ProfileScreen(navController) }
    }
}