package com.alaishat.ahmed.mobostore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
fun AppHeader(
    title: String,
    leftIconId: Int = R.drawable.ic_arrow_left,
    rightIconId: Int? = null,
    onClickLeftIcon: () -> Unit,
    onClickRightIcon: (() -> Unit)? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 12.dp)
    ) {
        Image(
            painter = painterResource(id = leftIconId),
            contentDescription = "",
            modifier = Modifier
                .size(48.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = true, radius = 24.dp),
                    onClick = onClickLeftIcon
                )
                .padding(horizontal = 12.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .weight(1f),
            textAlign = TextAlign.Center
        )
        if (rightIconId != null)
            Image(
                painter = painterResource(id = rightIconId),
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(bounded = true, radius = 24.dp),
                        onClick = onClickRightIcon ?: {}
                    )
                    .padding(horizontal = 12.dp)
            )
        else HorizontalSpacer(width = 48.dp)
    }
}