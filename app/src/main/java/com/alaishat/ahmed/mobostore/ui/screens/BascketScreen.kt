package com.alaishat.ahmed.mobostore.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.BasketItem
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
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasketScreen(navController: NavHostController) {
//    val ctx = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppHeader(
            "Basket",
            onClickLeftIcon = { navController.popBackStack() },
            rightIconId = R.drawable.ic_delete,
            onClickRightIcon = {})
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                VerticalSpacer(height = 30.dp)
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .background(
                            color = Color(0xFFD3F2FF),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_notification), contentDescription = "")
                    HorizontalSpacer(width = 4.dp)
                    Text(
                        text = "Delivery for FREE until the end of the month",
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
            }
            val count = 3
            items(count) { item ->
                BasketItem(Modifier.padding(top = 15.dp, start = 30.dp, end = 30.dp))
            }

            item {
                Column(
                    Modifier.padding(horizontal = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    VerticalSpacer(height = 20.dp)
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
                    PrimaryButton(text = "Checkout", onClick = { gotoCheckout(navController) })
                }
            }
//            if (count == 0)
//                item(span = { GridItemSpan(this.maxLineSpan) }, content = { NoFavorites() })
        }
    }
}


private fun gotoCheckout(navController: NavController) {
    navController.navigate(Screen.Checkout.route)
}

@Preview(showBackground = true)
@Composable
fun BasketPreview() {
    MoboStoreTheme {
        BasketScreen(rememberNavController())
    }
}