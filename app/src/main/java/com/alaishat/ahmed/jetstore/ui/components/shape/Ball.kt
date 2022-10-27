package com.alaishat.ahmed.jetstore.ui.components.shape

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by Ahmed Al-Aishat on Aug/16/2022.
 * JetStore Project.
 */
@Composable
fun Ball(
    modifier: Modifier = Modifier,
    size: Dp = 12.dp,
    color: Color = Color.Gray,
) {
    Canvas(
        modifier = modifier
            .width(size)
            .height(size)
            .clipToBounds()
            .background(color, CircleShape)
    ) {
        val canvasWidth = this.size.width
        val canvasHeight = this.size.height
        drawCircle(
            color = color,
            center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
            radius = this.size.minDimension / 4
        )
    }
}

@Preview
@Composable
fun BallPreview() {
    Ball(
        size = 32.dp,
        color = Color.Red,
    )
}