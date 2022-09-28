package com.alaishat.ahmed.mobostore.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Created by Ahmed Al-Aishat on Aug/17/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
fun RowScope.SpaceFiller() {
    Spacer(modifier = Modifier.weight(1f))
}

@Composable
fun ColumnScope.SpaceFiller() {
    Spacer(modifier = Modifier.weight(1f))
}