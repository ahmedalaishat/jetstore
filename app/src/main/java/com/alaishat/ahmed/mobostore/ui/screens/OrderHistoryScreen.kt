package com.alaishat.ahmed.mobostore.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.data.products.appleAirPods
import com.alaishat.ahmed.mobostore.ui.components.EmptyItems
import com.alaishat.ahmed.mobostore.ui.components.ProductContent
import com.alaishat.ahmed.mobostore.ui.components.headers.AppHeader
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme

/**
 * Created by Ahmed Al-Aishat on Aug/04/2022.
 * Mobo Store Project.
 */
@Composable
fun HistoryScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppHeader("Order History", onClickLeftIcon = { navController.popBackStack() })
        LazyVerticalGrid(
            modifier = Modifier.fillMaxHeight(),
            contentPadding = PaddingValues(vertical = 20.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            val count = 0
            items(count) { item ->
                val offset = if (item % 2 == 0) 0.dp else 50.dp
                ProductContent(appleAirPods, Modifier.padding(top = offset, start = 35.dp, end = 35.dp))
            }
            if (count == 0)
                item(span = { GridItemSpan(this.maxLineSpan) }, content = { NoHistory() })
        }
    }
}

@Composable
private fun NoHistory() {
    EmptyItems(
        imageId = R.drawable.saly_not_history,
        titleText = "No history yet",
        subtitle = "Hit the orange button down\n" +
                "below to Create an order",
        buttonText = "Start ordering",
        onClickButton = { }
    )
}

@Preview(showBackground = true)
@Composable
fun HistoryPreview() {
    MoboStoreTheme {
        HistoryScreen(rememberNavController())
    }
}