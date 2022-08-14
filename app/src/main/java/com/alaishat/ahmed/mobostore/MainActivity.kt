package com.alaishat.ahmed.mobostore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.ui.components.BottomBar
import com.alaishat.ahmed.mobostore.ui.navigation.Screen
import com.alaishat.ahmed.mobostore.ui.screens.*
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val loggedIn = false // AHMED_TODO check if the user is logged in
            val startDestination = if (loggedIn) Screen.Home.route else Screen.OnBoarding.route
            val bottomBarState = rememberSaveable { (mutableStateOf(loggedIn)) }


            val navBackStackEntry by navController.currentBackStackEntryAsState()

            when (navBackStackEntry?.destination?.route) {
                Screen.Home.route -> bottomBarState.value = true
                Screen.Favorites.route -> bottomBarState.value = true
                Screen.Profile.route -> bottomBarState.value = true
                Screen.Basket.route -> bottomBarState.value = true
                else -> bottomBarState.value = false
            }


            MoboStoreTheme {
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
                        composable(Screen.Search.route) { SearchScreen(navController) }
                        composable(Screen.OrderHistory.route) { HistoryScreen(navController) }
                        composable(Screen.NoConnection.route) { NoConnectionScreen(navController) }
                    }
                }
            }
        }
    }
}