package com.alaishat.ahmed.jetstore.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.jetstore.R
import com.alaishat.ahmed.jetstore.ui.components.EmptyItems
import com.alaishat.ahmed.jetstore.ui.components.ProductContent
import com.alaishat.ahmed.jetstore.ui.components.headers.AppHeader
import com.alaishat.ahmed.jetstore.ui.navigation.Screen
import com.alaishat.ahmed.jetstore.ui.screens.home.HomeViewModel
import com.alaishat.ahmed.jetstore.ui.theme.JetStoreTheme
import com.alaishat.ahmed.jetstore.utils.previousHiltViewModel

/**
 * Created by Ahmed Al-Aishat on Aug/01/2022.
 * JetStore Project.
 */
@Composable
fun FavoritesScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = navController.previousHiltViewModel()
) {
    // UiState
    val uiState by homeViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppHeader(stringResource(R.string.favorites), onClickLeftIcon = { navController.popBackStack() })
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 20.dp, bottom = 50.dp, start = 24.dp, end = 24.dp),
            columns = GridCells.Adaptive(150.dp),
            horizontalArrangement = Arrangement.spacedBy(35.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            var columnsCount = 2
            // get the current lineSpan count to use it in offset calculation
            item(
                span = {
                    columnsCount = maxLineSpan
                    GridItemSpan(this.maxLineSpan)
                },
                content = {})

            val favorites = uiState.homeCategories.flatMap { it.products }.filter { uiState.favorites.contains(it.id) }
            items(favorites.size) { position ->
                val absOffset = if (position % columnsCount == 0) 0.dp else 40.dp
                ProductContent(
                    product = favorites[position],
                    modifier = Modifier.absoluteOffset(0.dp, absOffset),
                    onProductClicked = { navController.navigate(route = "${Screen.Product.route}/${favorites[position].id}") }
                )
            }
            if (favorites.isEmpty())
                item(
                    span = { GridItemSpan(this.maxLineSpan) },
                    content = { NoFavorites { navController.popBackStack() } }
                )
        }
    }
}

@Composable
private fun NoFavorites(onClickButton: () -> Unit) {
    EmptyItems(
        imageId = R.drawable.sally_no_favorites,
        titleText = stringResource(R.string.favorites_empty_title),
        subtitle = stringResource(R.string.favorites_empty_subtitle),
        buttonText = stringResource(R.string.start_ordering),
        onClickButton = onClickButton
    )
}

@Preview(showBackground = true)
@Composable
fun FavoritesPreview() {
    JetStoreTheme {
        FavoritesScreen(rememberNavController())
    }
}