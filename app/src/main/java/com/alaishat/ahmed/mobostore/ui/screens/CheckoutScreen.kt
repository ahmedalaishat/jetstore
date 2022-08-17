package com.alaishat.ahmed.mobostore.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.AppButton
import com.alaishat.ahmed.mobostore.ui.components.AppHeader
import com.alaishat.ahmed.mobostore.ui.components.HorizontalSpacer
import com.alaishat.ahmed.mobostore.ui.components.VerticalSpacer
import com.alaishat.ahmed.mobostore.ui.theme.AppTypefaceTokens
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Ahmed Al-Aishat on Aug/16/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun CheckoutScreen(navController: NavHostController, scope: CoroutineScope, modalState: ModalBottomSheetState) {
//    val ctx = LocalContext.current

    /// to handle back press when the sheet is opened
    BackHandler(modalState.isVisible) {
        scope.launch {
            modalState.animateTo(ModalBottomSheetValue.Hidden)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppHeader(
            "Confirm and pay",
            onClickLeftIcon = { navController.popBackStack() },
        )
        VerticalSpacer(height = 40.dp)
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 50.dp)
        ) {
            Row {
                Text(
                    text = "Shipping information",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "change",
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            VerticalSpacer(height = 20.dp)
            ShippingInfo()
            VerticalSpacer(height = 20.dp)
            Text(
                text = "Payment Method",
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Start
            )
            VerticalSpacer(height = 20.dp)
            PaymentMethods()
            Spacer(modifier = Modifier.weight(1f))
            Row {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Total",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = AppTypefaceTokens.WeightRegular),
                    color = MaterialTheme.colorScheme.onSurface,
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
            VerticalSpacer(height = 50.dp)
            AppButton(
                onClick = { scope.launch { modalState.animateTo(ModalBottomSheetValue.Expanded) } },
                text = "Confirm and pay",
            )
            VerticalSpacer(height = 20.dp)
        }
    }
}

@Composable
private fun ShippingInfo(
    name: String = "Rosina Doe",
    location: String = "43 Oxford Road M13 4GR Manchester, UK",
    phoneNumber: String = "+234 9011039271"
) {
    Card(
        Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(vertical = 5.dp, horizontal = 10.dp)) {
            ShippingInfoRow(R.drawable.ic_profile, name)
            ShippingInfoRow(R.drawable.ic_location, location)
            ShippingInfoRow(R.drawable.ic_call, phoneNumber)
        }
    }
}

@Composable
private fun ShippingInfoRow(iconId: Int, name: String) {
    Row(Modifier.padding(vertical = 5.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "",
            tint = Color.Unspecified
        )
        HorizontalSpacer(width = 10.dp)
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = AppTypefaceTokens.WeightRegular),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun PaymentMethods(
    radioOptions: List<Int> = listOf(R.drawable.visa, R.drawable.master, R.drawable.bank)
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[2]) }
    Card(Modifier.fillMaxWidth()) {
        Column {
            radioOptions.forEach { imageId ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (imageId == selectedOption),
                            onClick = { onOptionSelected(imageId) }
                        )
                        .padding(vertical = 5.dp, horizontal = 5.dp),
                    verticalAlignment = CenterVertically
                ) {
                    RadioButton(
                        selected = (imageId == selectedOption),
                        onClick = { onOptionSelected(imageId) }
                    )
                    Image(painter = painterResource(id = imageId), contentDescription = "")
                    HorizontalSpacer(width = 16.dp)
                    Text(
                        text = "**** **** **** 1234",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = AppTypefaceTokens.WeightRegular),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun CheckoutPreview() {
    MoboStoreTheme {
        CheckoutScreen(
            rememberNavController(),
            rememberCoroutineScope(),
            rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        )
    }
}