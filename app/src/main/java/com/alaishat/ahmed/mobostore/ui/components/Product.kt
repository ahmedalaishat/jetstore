package com.alaishat.ahmed.mobostore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.utils.advancedShadow

/**
 * Created by Ahmed Al-Aishat on Aug/03/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
fun Product(
    modifier: Modifier = Modifier,
    onProductClicked: () -> Unit = {},
    showSecondaryText: Boolean = true,
) {
    Box(modifier) {
        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.aspectRatio(4f))
            Card {
                Column(
                    modifier = Modifier
                        .clickable { onProductClicked() }
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.aspectRatio(1.8f))
                    Text(
                        text = "Apple Watch",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                    )
                    if (showSecondaryText) {
                        Text(
                            text = "Series 6 . Red",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center,
                        )
                        VerticalSpacer(height = 16.dp)
                    }
                    Text(
                        text = "\$ 359",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.aspectRatio(5f))
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.mask_group),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .align(Alignment.TopCenter),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductPreview() {
    MoboStoreTheme {
        Product(onProductClicked = {})
    }
}