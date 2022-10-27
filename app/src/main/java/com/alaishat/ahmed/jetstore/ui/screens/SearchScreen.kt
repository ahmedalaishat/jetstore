package com.alaishat.ahmed.jetstore.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.jetstore.R
import com.alaishat.ahmed.jetstore.ui.components.EmptyItems
import com.alaishat.ahmed.jetstore.ui.components.ProductContent
import com.alaishat.ahmed.jetstore.ui.components.VerticalSpacer
import com.alaishat.ahmed.jetstore.ui.components.headers.SearchHeader
import com.alaishat.ahmed.jetstore.ui.navigation.Screen
import com.alaishat.ahmed.jetstore.ui.screens.home.HomeViewModel
import com.alaishat.ahmed.jetstore.ui.theme.JetStoreTheme
import com.alaishat.ahmed.jetstore.utils.header
import com.alaishat.ahmed.jetstore.utils.previousHiltViewModel

/**
 * Created by Ahmed Al-Aishat on Aug/03/2022.
 * JetStore Project.
 */
@Composable
fun SearchScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = navController.previousHiltViewModel()
) {

    // UiState of the HomeScreen
    val uiState by homeViewModel.uiState.collectAsState()
    val products = uiState.searchProducts

    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        VerticalSpacer(height = 10.dp)
        SearchHeader(
            searchValue = uiState.searchInput,
            onValueChange = { homeViewModel.onSearchInputChanged(it) },
            inputEnabled = true,
            focusRequester = focusRequester,
            leftIconId = R.drawable.ic_arrow_left,
            onClickLeftIcon = {
                homeViewModel.searchOpened(false)
                navController.popBackStack()
            }
        )
        if (uiState.searchInput.isNotEmpty())
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

                header {
                    if (products.isNotEmpty())
                        Text(
                            text = stringResource(R.string.found_results, products.size),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(vertical = 10.dp),
                            textAlign = TextAlign.Center
                        )
                }
                items(products.size) { position ->
                    val absOffset = if (position % columnsCount == 0) 0.dp else 40.dp
                    ProductContent(
                        product = products[position],
                        modifier = Modifier.absoluteOffset(0.dp, absOffset),
                        showSecondaryText = false,
                        onProductClicked = { navController.navigate(route = "${Screen.Product.route}/${products[position].id}") }
                    )
                }
                if (products.isEmpty())
                    item(span = { GridItemSpan(this.maxLineSpan) }, content = { NoItems() })
            }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
private fun NoItems() {
    EmptyItems(
        imageId = R.drawable.sally_no_items,
        titleText = stringResource(R.string.search_empty_title),
        subtitle = stringResource(R.string.search_empty_subtitle),
    )
}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    JetStoreTheme {
        SearchScreen(rememberNavController())
    }
}