package com.alaishat.ahmed.mobostore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
            val loggedIn = true // AHMED_TODO check if the user is logged in
            val startDestination = if (loggedIn) Screen.Home.route else Screen.OnBoarding.route

            MoboStoreTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomBar(navController = navController) }
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
                        composable(Screen.Basket.route) { BascketScreen(navController) }
                    }
                }
            }
        }
    }
}