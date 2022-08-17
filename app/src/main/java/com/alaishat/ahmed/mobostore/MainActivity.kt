package com.alaishat.ahmed.mobostore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.ui.components.BottomBar
import com.alaishat.ahmed.mobostore.ui.components.modal.PaymentModal
import com.alaishat.ahmed.mobostore.ui.navigation.Screen
import com.alaishat.ahmed.mobostore.ui.screens.*
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val loggedIn = true // AHMED_TODO check if the user is logged in
            val startDestination = if (loggedIn) Screen.Home.route else Screen.OnBoarding.route
            val bottomBarState = rememberSaveable { (mutableStateOf(loggedIn)) }


            val navBackStackEntry by navController.currentBackStackEntryAsState()
//            val currentRoute = navBackStackEntry?.destination?.route
//            val isLoginStack = currentRoute == Screen.OnBoarding.route || currentRoute == Screen.Login.route
//            bottomBarState.value = !isLoginStack
            bottomBarState.value = when (navBackStackEntry?.destination?.route) {
                Screen.Home.route, Screen.Favorites.route,
                Screen.Profile.route, Screen.Basket.route -> true
                else -> false
            }


            val modalBottomSheetState = rememberModalBottomSheetState(
                initialValue = ModalBottomSheetValue.Hidden
            )
            val scope = rememberCoroutineScope()

            MoboStoreTheme {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    ModalBottomSheetLayout(
                        sheetContent = { PaymentModal() },
                        sheetState = modalBottomSheetState,
                        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                    ) {
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            bottomBar = { BottomBar(navController = navController, bottomBarState) }
                        ) { innerPadding ->
                            NavHost(
                                navController = navController,
                                startDestination = startDestination,
                                modifier = Modifier.padding(innerPadding)
                            ) {
                                composable(Screen.OnBoarding.route) { OnBoardingScreen(navController) }
                                composable(Screen.Login.route) { LoginScreen(navController) }
                                composable(Screen.Home.route) { HomeScreen(navController) }
                                composable(Screen.Favorites.route) { FavoritesScreen(navController) }
                                composable(Screen.Profile.route) { ProfileScreen(navController) }
                                composable(Screen.Basket.route) { BasketScreen(navController) }
                                composable(Screen.Checkout.route) {
                                    CheckoutScreen(
                                        navController,
                                        scope,
                                        modalBottomSheetState
                                    )
                                }
                                composable(Screen.SingleItem.route) { SingleItemScreen(navController) }
                                composable(Screen.Search.route) { SearchScreen(navController) }
                                composable(Screen.OrderHistory.route) { HistoryScreen(navController) }
                                composable(Screen.NoConnection.route) { NoConnectionScreen(navController) }
                            }
                        }
                    }
                }
            }
        }
    }
}