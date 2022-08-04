package com.alaishat.ahmed.mobostore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.screens.HomeScreen
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.utils.animatePage

/**
 * Created by Ahmed Al-Aishat on Aug/03/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
fun Product(modifier: Modifier = Modifier) {
    Box(modifier) {
        Card(
            modifier = Modifier
                .padding(top = 45.dp)
                .width(220.dp)
                .height(270.dp),
        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                VerticalSpacer(height = 140.dp)
                Text(text = "Apple Watch", style = MaterialTheme.typography.titleLarge, color = Color.Black)
                Text(
                    text = "Series 6 . Red",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                VerticalSpacer(height = 16.dp)
                Text(
                    text = "\$ 359",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.mask_group),
            contentDescription = null,
            modifier = Modifier.size(220.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductPreview() {
    MoboStoreTheme {
        Product()
    }
}