package com.alaishat.ahmed.mobostore.ui.screens.basket

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.BasketItem
import com.alaishat.ahmed.mobostore.ui.components.EmptyItems
import com.alaishat.ahmed.mobostore.ui.components.HorizontalSpacer
import com.alaishat.ahmed.mobostore.ui.components.VerticalSpacer
import com.alaishat.ahmed.mobostore.ui.components.buttons.PrimaryButton
import com.alaishat.ahmed.mobostore.ui.components.headers.AppHeader
import com.alaishat.ahmed.mobostore.ui.navigation.Screen
import com.alaishat.ahmed.mobostore.ui.theme.AppTypefaceTokens
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme

/**
 * Created by Ahmed Al-Aishat on Aug/01/2022.
 * Mobo Store Project.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasketScreen(
    navController: NavHostController,
    basketViewModel: BasketViewModel = hiltViewModel(),
) {
    // UiState of the HomeScreen
    val uiState by basketViewModel.uiState.collectAsState()
    val total = uiState.basketProducts.sumOf { it.price * it.count }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppHeader(
            stringResource(R.string.basket),
            onClickLeftIcon = { navController.popBackStack() },
            rightIconId = R.drawable.ic_delete,
            onClickRightIcon = {})
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .background(
                    color = Color(0xFFD3F2FF),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Image(painter = painterResource(id = R.drawable.ic_notification), contentDescription = "")
            HorizontalSpacer(width = 4.dp)
            Text(
                text = stringResource(R.string.basket_hint),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
                    shadow = Shadow(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                        offset = Offset(0.0f, 10.0f),
                        blurRadius = 10f
                    )
                ),
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (uiState.basketProducts.isEmpty())
                item(
//                    span = { GridItemSpan(this.maxLineSpan) },
                    content = { NoProducts { navController.popBackStack() } }
                )
            else {
                items(uiState.basketProducts.size) { position ->
                    val product = uiState.basketProducts[position]
                    BasketItem(
                        product = product,
                        increaseCount = { basketViewModel.increaseBasketProductCount(product.id) },
                        decreaseCount = { basketViewModel.decreaseBasketProductCount(product.id) },
                        onClickProduct = { navController.navigate(route = "${Screen.Product.route}/$it") },
                        Modifier.padding(top = 15.dp, start = 30.dp, end = 30.dp)
                    )
                }

                item {
                    Column(
                        Modifier
                            .padding(horizontal = 50.dp)
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        VerticalSpacer(height = 20.dp)
                        Row {
                            Text(
                                modifier = Modifier.weight(1f),
                                text = stringResource(R.string.total),
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
                            text = stringResource(R.string.checkout),
                            onClick = { gotoCheckout(navController) })
                    }
                }
            }
//            if (count == 0)
//                item(span = { GridItemSpan(this.maxLineSpan) }, content = { NoFavorites() })
        }
    }
}

@Composable
private fun NoProducts(onClickButton: () -> Unit) {
    EmptyItems(
        imageId = R.drawable.sally_no_favorites,
        titleText = stringResource(R.string.basket_empty_title),
        subtitle = stringResource(R.string.favorites_empty_subtitle),
        buttonText = stringResource(R.string.start_ordering),
        onClickButton = onClickButton
    )
}


private fun gotoCheckout(navController: NavController) {
    navController.navigate(Screen.Checkout.route)
}

@Preview(showBackground = true)
@Composable
fun BasketPreview() {
    MoboStoreTheme {
        BasketScreen(rememberNavController(), hiltViewModel())
    }
}