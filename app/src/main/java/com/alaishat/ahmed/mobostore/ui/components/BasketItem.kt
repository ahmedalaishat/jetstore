package com.alaishat.ahmed.mobostore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.data.products.samsungBuds
import com.alaishat.ahmed.mobostore.model.BasketProduct
import com.alaishat.ahmed.mobostore.ui.components.buttons.IconButton
import com.alaishat.ahmed.mobostore.ui.theme.AppTypefaceTokens
import com.alaishat.ahmed.mobostore.utils.silentClickable

/**
 * Created by Ahmed Al-Aishat on Aug/15/2022.
 * Mobo Store Project.
 */
@Composable
fun BasketItem(
    product: BasketProduct,
    increaseCount: () -> Unit,
    decreaseCount: () -> Unit,
    onClickProduct: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    Box(modifier) {
        Card(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp))
                .silentClickable { onClickProduct(product.id) },
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
//                AnimatedVisibility(visible = (painter.state is AsyncImagePainter.State.Loading)) {
//                    CircularProgressIndicator()
//                }
                Image(
                    painter = rememberAsyncImagePainter(product.imageId),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(10.dp)
                        .width(120.dp)
                        .height(158.dp),
                )

                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    Text(text = product.title, style = MaterialTheme.typography.titleLarge, color = Color.Black)
                    VerticalSpacer(height = 12.dp)
                    Text(
                        text = "\$ ${product.price}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    VerticalSpacer(height = 12.dp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(R.string.quantity),
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = AppTypefaceTokens.WeightRegular),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        HorizontalSpacer(width = 12.dp)
                        IconButton(
                            onClick = { if (product.count > 1) decreaseCount() },
                            iconId = R.drawable.ic_remove,
                            iconSize = 8.dp,
                            shape = RoundedCornerShape(4.dp),
                            backgroundColor = MaterialTheme.colorScheme.secondary,
                            iconTint = MaterialTheme.colorScheme.onSecondary,
                            paddingValue = PaddingValues(8.dp)
                        )
                        Text(
                            text = product.count.toString(),
                            modifier = Modifier
                                .padding(4.dp)
                                .defaultMinSize(minWidth = 20.dp),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.labelSmall,
                            textAlign = TextAlign.Center
                        )
                        IconButton(
                            onClick = { increaseCount() },
                            iconId = R.drawable.ic_add,
                            iconSize = 8.dp,
                            shape = RoundedCornerShape(4.dp),
                            backgroundColor = MaterialTheme.colorScheme.secondary,
                            iconTint = MaterialTheme.colorScheme.onSecondary,
                            paddingValue = PaddingValues(8.dp)
                        )
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
        BasketItem(samsungBuds.toBasketProduct(), {}, {}, {})
    }
}