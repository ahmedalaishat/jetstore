package com.alaishat.ahmed.mobostore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alaishat.ahmed.mobostore.R

/**
 * Created by Ahmed Al-Aishat on Aug/04/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
fun EmptyItems(
    imageId: Int,
    titleText: String,
    descriptionText: String? = null,
    buttonText: String? = null,
    onClickButton: (() -> Unit)? = null,
) {
    Column(
        Modifier.padding(horizontal = 35.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = imageId), contentDescription = null)
        Text(
            text = titleText,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        if (descriptionText != null) {
            VerticalSpacer(height = 20.dp)
            Text(
                text = descriptionText,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        if (onClickButton != null && buttonText != null) {
            VerticalSpacer(height = 30.dp)
            AppButton(
                text = buttonText,
                onClick = onClickButton,
                containerColor = MaterialTheme.colorScheme.secondary,
                textColor = MaterialTheme.colorScheme.onSecondary,
            )
        }
    }
}
