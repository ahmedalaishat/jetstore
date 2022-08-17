package com.alaishat.ahmed.mobostore.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by Ahmed Al-Aishat on Jul/31/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    loading: Boolean = false
) {
    Box(modifier = modifier) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .width(314.dp)
                .height(70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                disabledContainerColor = containerColor.copy(alpha = 0.7f)
            ),
            shape = RoundedCornerShape(12.dp),
            enabled = !loading
        ) {
            if (loading)
                BallPulseSyncIndicator(
                    modifier = Modifier.absoluteOffset(0.dp, 0.dp),
                    ballsColor = contentColor,
                    ballSize = 8.dp
                )
            else
                Text(
                    text = text,
                    color = contentColor,
                    style = MaterialTheme.typography.labelLarge
                )
        }
    }
}