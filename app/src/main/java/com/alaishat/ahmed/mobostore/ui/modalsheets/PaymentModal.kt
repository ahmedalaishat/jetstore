package com.alaishat.ahmed.mobostore.ui.modalsheets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.VerticalSpacer
import com.alaishat.ahmed.mobostore.ui.components.buttons.PrimaryButton
import com.alaishat.ahmed.mobostore.ui.screens.basket.BasketViewModel
import com.alaishat.ahmed.mobostore.ui.theme.AppTypefaceTokens
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme

/**
 * Created by Ahmed Al-Aishat on Aug/16/2022.
 * Mobo Store Project.
 */
@Composable
@ExperimentalMaterialApi
fun PaymentModal(
    basketViewModel: BasketViewModel = hiltViewModel()
) {
    val uiState by basketViewModel.uiState.collectAsState()
    val productsCount = uiState.basketProducts.count()
    val total = uiState.basketProducts.sumOf { it.price * it.count }


    VerticalSpacer(height = 20.dp)
    Column(Modifier.padding(horizontal = 50.dp, vertical = 20.dp)) {
        Row {
            Text(
                text = stringResource(id = R.string.confirm_and_pay),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start
            )
            Text(
                buildAnnotatedString {
                    append(stringResource(R.string.products))
                    withStyle(
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurface)
                            .toSpanStyle()
                    ) {
                        append(" $productsCount")
                    }
                },
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        VerticalSpacer(height = 40.dp)
        Card(
            backgroundColor = Color(0xFFF4F6FA),
            border = BorderStroke(1.dp, Color(0xFFD6D9E0)),
            elevation = 0.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(Modifier.padding(vertical = 25.dp, horizontal = 15.dp)) {
                Row {
                    Text(
                        text = stringResource(R.string.my_credit_card),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = AppTypefaceTokens.WeightRegular),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start,
                    )
                    Image(
                        painter = painterResource(id = uiState.selectedPaymentCard.cardImage),
                        contentDescription = ""
                    )
                }
                VerticalSpacer(height = 15.dp)
                Text(
                    text = uiState.selectedPaymentCard.carNumber,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = AppTypefaceTokens.WeightRegular),
                    textAlign = TextAlign.Start,
                )
                VerticalSpacer(height = 15.dp)
                Row {
                    Text(
                        text = "Rosina Doe",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "04/26",
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
        VerticalSpacer(height = 40.dp)
        Row {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.total),
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = AppTypefaceTokens.WeightRegular),
                textAlign = TextAlign.Start
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "\$ $total",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.End
            )
        }
        VerticalSpacer(height = 40.dp)
        PrimaryButton(text = stringResource(R.string.pay_now), onClick = { pay() })
    }
}

fun pay() {
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun PaymentModalPreview() {
    MoboStoreTheme {
        PaymentModal()
    }
}