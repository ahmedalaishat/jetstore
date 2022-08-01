package com.alaishat.ahmed.mobostore.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * Created by Ahmed Al-Aishat on Aug/01/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
fun VerticalSpacer(height: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(height))
}