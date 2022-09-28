package com.alaishat.ahmed.mobostore.ui.components.modalsheets

import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.VerticalSpacer
import com.alaishat.ahmed.mobostore.ui.components.buttons.PrimaryButton
import com.alaishat.ahmed.mobostore.ui.theme.AppTypefaceTokens
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme

/**
 * Created by Ahmed Al-Aishat on Aug/16/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
@ExperimentalMaterialApi
fun PaymentModal() {
    val context = LocalContext.current

    VerticalSpacer(height = 20.dp)
    Column(Modifier.padding(horizontal = 50.dp, vertical = 20.dp)) {
        Row {
            Text(
                text = "Confirm and pay",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start
            )
            Text(
                buildAnnotatedString {
                    append("Products:")
                    withStyle(
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurface)
                            .toSpanStyle()
                    ) {
                        append(" 2")
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
                        text = "My credit card",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = AppTypefaceTokens.WeightRegular),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start,
                    )
                    Image(painter = painterResource(id = R.drawable.visa), contentDescription = "")
                }
                VerticalSpacer(height = 15.dp)
                Text(
                    text = "**** **** **** 1234",
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
                text = "Total",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = AppTypefaceTokens.WeightRegular),
                textAlign = TextAlign.Start
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "\$ 954",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.End
            )
        }
        VerticalSpacer(height = 40.dp)
        PrimaryButton(text = "Pay now", onClick = { pay(context) })
    }
}

fun pay(context: Context) {
    Toast.makeText(context, "Error occurred", Toast.LENGTH_SHORT).show()
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun PaymentModalPreview() {
    MoboStoreTheme {
        PaymentModal()
    }
}