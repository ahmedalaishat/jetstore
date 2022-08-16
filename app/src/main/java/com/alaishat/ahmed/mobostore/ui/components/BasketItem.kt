package com.alaishat.ahmed.mobostore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.theme.AppTypefaceTokens

/**
 * Created by Ahmed Al-Aishat on Aug/15/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
fun BasketItem(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf(1) }

    Box(modifier) {
        Card(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp)),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
//                AnimatedVisibility(visible = (painter.state is AsyncImagePainter.State.Loading)) {
//                    CircularProgressIndicator()
//                }
                Image(
                    painter = rememberAsyncImagePainter(R.drawable.item_basket),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(10.dp)
                        .width(120.dp)
                        .height(158.dp),
                )

                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    Text(text = "Apple Watch", style = MaterialTheme.typography.titleLarge, color = Color.Black)
                    VerticalSpacer(height = 12.dp)
                    Text(
                        text = "\$ 359",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    VerticalSpacer(height = 12.dp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Quantity",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = AppTypefaceTokens.WeightRegular),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        HorizontalSpacer(width = 12.dp)
                        AppIconButton(onClick = { if (count > 1) count-- }, iconId = R.drawable.ic_remove)
                        Text(
                            text = count.toString(),
                            modifier = Modifier
                                .padding(4.dp)
                                .defaultMinSize(minWidth = 20.dp),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.labelSmall,
                            textAlign = TextAlign.Center
                        )
                        AppIconButton(onClick = { count++ }, iconId = R.drawable.ic_add)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BasketItemPreview() {
    MaterialTheme {
        BasketItem()
    }
}