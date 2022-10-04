package com.alaishat.ahmed.mobostore.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.model.PaymentMethod
import com.alaishat.ahmed.mobostore.ui.components.HorizontalSpacer
import com.alaishat.ahmed.mobostore.ui.components.VerticalSpacer
import com.alaishat.ahmed.mobostore.ui.components.buttons.PrimaryButton
import com.alaishat.ahmed.mobostore.ui.components.headers.AppHeader
import com.alaishat.ahmed.mobostore.ui.screens.basket.BasketViewModel
import com.alaishat.ahmed.mobostore.ui.theme.AppTypefaceTokens
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Ahmed Al-Aishat on Aug/16/2022.
 * Mobo Store Project.
 */
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun CheckoutScreen(
    navController: NavHostController,
    scope: CoroutineScope,
    modalState: ModalBottomSheetState,
    basketViewModel: BasketViewModel = hiltViewModel()
) {
    val uiState by basketViewModel.uiState.collectAsState()
    val total = uiState.basketProducts.sumOf { it.price * it.count }

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
            stringResource(id = R.string.confirm_and_pay),
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
                    text = stringResource(R.string.shipping_information),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = stringResource(R.string.change),
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            VerticalSpacer(height = 20.dp)
            ShippingInfo()
            VerticalSpacer(height = 20.dp)
            Text(
                text = stringResource(R.string.payment_method),
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Start
            )
            VerticalSpacer(height = 20.dp)
            PaymentMethods(
                uiState.paymentMethods,
                uiState.selectedPaymentCard
            ) { basketViewModel.selectPaymentCard(it) }
            Spacer(modifier = Modifier.weight(1f))
            Row {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.total),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = AppTypefaceTokens.WeightRegular),
                    color = MaterialTheme.colorScheme.onSurface,
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
            VerticalSpacer(height = 50.dp)
            PrimaryButton(
                text = stringResource(R.string.confirm_and_pay),
                onClick = { scope.launch { modalState.animateTo(ModalBottomSheetValue.Expanded) } },
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
    paymentMethods: List<PaymentMethod>,
    selectedPaymentCard: PaymentMethod,
    selectPaymentCard: (String) -> Unit,
) {
    Card(Modifier.fillMaxWidth()) {
        Column {
            paymentMethods.forEach { paymentCard ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (paymentCard == selectedPaymentCard),
                            onClick = { selectPaymentCard(paymentCard.carNumber) }
                        )
                        .padding(vertical = 5.dp, horizontal = 5.dp),
                    verticalAlignment = CenterVertically
                ) {
                    RadioButton(
                        selected = (paymentCard == selectedPaymentCard),
                        onClick = { selectPaymentCard(paymentCard.carNumber) }
                    )
                    Image(painter = painterResource(id = paymentCard.cardImage), contentDescription = "")
                    HorizontalSpacer(width = 16.dp)
                    Text(
                        text = paymentCard.carNumber,
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