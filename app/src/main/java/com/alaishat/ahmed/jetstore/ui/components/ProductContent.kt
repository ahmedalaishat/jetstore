package com.alaishat.ahmed.jetstore.ui.components

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaishat.ahmed.jetstore.data.products.appleAirPods
import com.alaishat.ahmed.jetstore.model.Product
import com.alaishat.ahmed.jetstore.ui.theme.JetStoreTheme
import com.alaishat.ahmed.jetstore.utils.silentClickable

/**
 * Created by Ahmed Al-Aishat on Aug/03/2022.
 * JetStore Project.
 */
@Composable
fun ProductContent(
    product: Product,
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
                        .silentClickable { onProductClicked() }
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.aspectRatio(1.8f))
                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                    )
                    if (showSecondaryText) {
                        Text(
                            text = product.subtitle,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center,
                        )
                        VerticalSpacer(height = 16.dp)
                    }
                    Text(
                        text = "\$ " + product.price,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.aspectRatio(5f))
                }
            }
        }
        Image(
            painter = painterResource(id = product.imageId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .aspectRatio(1f)
                .align(Alignment.TopCenter),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductPreview() {
    JetStoreTheme {
        ProductContent(appleAirPods, onProductClicked = {})
    }
}