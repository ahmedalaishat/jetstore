package com.alaishat.ahmed.mobostore.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * Created by Ahmed Al-Aishat on Aug/01/2022.
 * Mobo Store Project.
 */
@Composable
fun HorizontalSpacer(width: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.width(width))
}