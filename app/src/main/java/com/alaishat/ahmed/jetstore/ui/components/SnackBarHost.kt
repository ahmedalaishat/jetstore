package com.alaishat.ahmed.jetstore.ui.components

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by Ahmed Al-Aishat on Sep/29/2022.
 * JetStore Project.
 */
@Composable
fun JetStoreSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (SnackbarData) -> Unit = { Snackbar(it) }
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier
            .systemBarsPadding()
            // Limit the Snackbar width for large screens
            .wrapContentWidth(align = Alignment.Start)
            .widthIn(max = 550.dp),
        snackbar = snackbar
    )
}
