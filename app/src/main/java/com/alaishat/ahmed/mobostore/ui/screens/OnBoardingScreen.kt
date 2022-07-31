package com.alaishat.ahmed.mobostore.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.utils.advancedShadow
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Created by Ahmed Al-Aishat on Jul/31/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
fun OnBoardingScreen() {
    val ctx = LocalContext.current
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
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 50.dp),
            lineHeight = 50.sp,
            text = "Find your Gadget", color = Color.White,
            fontSize = 50.sp
        )
        Image(
            painter = painterResource(id = R.drawable.saly_onboarding),
            contentDescription = "",
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .absoluteOffset(0.dp, (-64).dp)
                .advancedShadow(color = Color(0xFF6350FF), 0.99f, 0.dp, 32.dp, 32.dp),
        )
        Button(
            onClick = { Toast.makeText(ctx, "Started", Toast.LENGTH_LONG).show() },
            modifier = Modifier
                .width(314.dp)
                .height(70.dp)
        ) {
            Text(text = "Get Started", color = MaterialTheme.colorScheme.secondary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoboStoreTheme {
        OnBoardingScreen()
    }
}