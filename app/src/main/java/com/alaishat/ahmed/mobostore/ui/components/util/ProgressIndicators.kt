package com.alaishat.ahmed.mobostore.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alaishat.ahmed.mobostore.ui.components.shape.Ball
import kotlinx.coroutines.delay

/**
 * Created by Ahmed Al-Aishat on Aug/16/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
fun BallPulseSyncIndicator(
    modifier: Modifier,
    ballsColor: Color = Color.Gray,
    ballSize: Dp = 12.dp
) {
    val animationValues = (1..3).map { index ->
        var animatedValue by remember { mutableStateOf(0f) }
        LaunchedEffect(key1 = Unit) {
            // Delaying using Coroutines
            delay(70L * index)
            animate(
                initialValue = -6f,
                targetValue = 6f,
                animationSpec = infiniteRepeatable(
                    // Remove delay property
                    animation = tween(durationMillis = 300),
                    repeatMode = RepeatMode.Reverse,
                )
            ) { value, _ -> animatedValue = value }
        }
        animatedValue
    }
    Row(modifier) {
        animationValues.forEach { animatedValue ->
            Ball(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .offset(y = animatedValue.dp),
                color = ballsColor,
                size = ballSize,
            )
        }
    }
}

@Composable
fun BallScaleIndicator() {
    // Creates the infinite transition
    val infiniteTransition = rememberInfiniteTransition()
    // Animate from 0f to 1f
    val animationProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800)
        )
    )
    Ball(
        modifier = Modifier
            .scale(animationProgress)
            .alpha(1 - animationProgress),
    )
}

/**
 * Full screen circular progress indicator
 */
@Composable
fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}