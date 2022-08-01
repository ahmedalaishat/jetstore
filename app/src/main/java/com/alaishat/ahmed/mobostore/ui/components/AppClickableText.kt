package com.alaishat.ahmed.mobostore.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

/**
 * Created by Ahmed Al-Aishat on Aug/01/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
fun AppClickableText(
    text: String,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.labelMedium,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Box(modifier = modifier) {
        ClickableText(
            text = AnnotatedString(text = text, spanStyle = style.toSpanStyle().copy(color = color)),
            onClick = onClick,
        )
    }
}