package com.alaishat.ahmed.mobostore.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.AppButton
import com.alaishat.ahmed.mobostore.ui.components.VerticalSpacer
import com.alaishat.ahmed.mobostore.ui.navigation.Screen
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.utils.advancedShadow
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Created by Ahmed Al-Aishat on Jul/31/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
fun OnBoardingScreen(navController: NavHostController) {
//    val ctx = LocalContext.current
    val systemUiController = rememberSystemUiController()

    val color = MaterialTheme.colorScheme.secondary
    SideEffect {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're in light theme
        systemUiController.setSystemBarsColor(
            color = Color(color.toArgb()),
            darkIcons = false
        )

        // setStatusBarColor() and setNavigationBarColor() also exist
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VerticalSpacer(height = 70.dp)
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            text = "Find your Gadget",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.displayLarge
        )
        Image(painter = painterResource(id = R.drawable.saly_onboarding), contentDescription = "")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .absoluteOffset(0.dp, (-64).dp)
                .advancedShadow(color = Color(0xFF6350FF), 0.99f, 0.dp, 32.dp, 32.dp),
        )
        AppButton(
            text = "Get Started",
            onClick = { gotoLogin(navController) },
            textColor = MaterialTheme.colorScheme.secondary,
            containerColor = Color.White
        )
        VerticalSpacer(height = 100.dp)
    }
}

fun gotoLogin(navController: NavController) {
    val options = NavOptions.Builder().setPopUpTo(Screen.OnBoarding.route, true).build()
    navController.navigate(Screen.Login.route, options)
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    MoboStoreTheme {
        OnBoardingScreen(rememberNavController())
    }
}