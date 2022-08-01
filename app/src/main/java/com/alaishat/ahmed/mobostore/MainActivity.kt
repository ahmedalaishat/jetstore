package com.alaishat.ahmed.mobostore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.ui.navigation.Screen
import com.alaishat.ahmed.mobostore.ui.screens.HomeScreen
import com.alaishat.ahmed.mobostore.ui.screens.LoginScreen
import com.alaishat.ahmed.mobostore.ui.screens.OnBoardingScreen
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val loggedIn = true // AHMED_TODO check if the user is logged in
            val startDestination = if (loggedIn) Screen.Home.route else Screen.OnBoarding.route

            MoboStoreTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavHost(navController = navController, startDestination = startDestination) {
                        composable(Screen.OnBoarding.route) { OnBoardingScreen(navController) }
                        composable(Screen.Login.route) { LoginScreen(navController) }
                        composable(Screen.Home.route) { HomeScreen(navController) }
                    }
                }
            }
        }
    }
}