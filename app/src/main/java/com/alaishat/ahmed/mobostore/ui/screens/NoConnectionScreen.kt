package com.alaishat.ahmed.mobostore.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.EmptyItems
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme

/**
 * Created by Ahmed Al-Aishat on Aug/04/2022.
 * Mobo Store Project.
 */
@Composable
fun NoConnectionScreen(onReload: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        EmptyItems(
            imageId = R.drawable.no_connection,
            titleText = "No internet Connection",
            subtitle = "Your internet connection is currently\n" +
                    "not available please check or try again.",
            buttonText = "Try again",
            onClickButton = onReload
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoConnectionPreview() {
    MoboStoreTheme {
        NoConnectionScreen { }
    }
}