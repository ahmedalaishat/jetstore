package com.alaishat.ahmed.mobostore.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.AppHeader
import com.alaishat.ahmed.mobostore.ui.components.EmptyItems
import com.alaishat.ahmed.mobostore.ui.components.Product
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.utils.header

/**
 * Created by Ahmed Al-Aishat on Aug/01/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
fun FavoritesScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxHeight(),
            contentPadding = PaddingValues(vertical = 20.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            val count = 0
            header {
                AppHeader("Favorites", onClickLeftIcon = { navController.popBackStack() })
            }
            items(count) { item ->
                val offset = if (item % 2 == 0) 0.dp else 50.dp
                Product(Modifier.padding(top = offset, start = 35.dp, end = 35.dp))
            }
            if (count == 0)
                item(span = { GridItemSpan(this.maxLineSpan) }, content = { NoFavorites() })
        }
    }
}

@Composable
private fun NoFavorites() {
    EmptyItems(
        imageId = R.drawable.sally_no_favorites,
        titleText = "No favorites yet",
        descriptionText = "Hit the orange button down\n" +
                "below to Create an order",
        buttonText = "Start ordering",
        onClickButton = { }
    )
}

@Preview(showBackground = true)
@Composable
fun FavoritesPreview() {
    MoboStoreTheme {
        FavoritesScreen(rememberNavController())
    }
}