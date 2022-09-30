package com.alaishat.ahmed.mobostore.ui.screens

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.EmptyItems
import com.alaishat.ahmed.mobostore.ui.components.ProductContent
import com.alaishat.ahmed.mobostore.ui.components.VerticalSpacer
import com.alaishat.ahmed.mobostore.ui.components.headers.SearchHeader
import com.alaishat.ahmed.mobostore.ui.screens.home.HomeViewModel
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.utils.header
import com.alaishat.ahmed.mobostore.utils.previousHiltViewModel

/**
 * Created by Ahmed Al-Aishat on Aug/03/2022.
 * Mobo Store Project.
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
                contentPadding = PaddingValues(vertical = 20.dp, horizontal = 24.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(35.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                header {
                    if (products.isNotEmpty())
                        Text(
                            text = "Found  ${products.size} results",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(vertical = 10.dp),
                            textAlign = TextAlign.Center
                        )
                }
                items(products.size) { position ->
//                val offset = if (item % 2 == 0) 0.dp else 50.dp
                    val absOffset = if (position % 2 == 0) 0.dp else 40.dp
                    ProductContent(
                        product = products[position],
                        modifier = Modifier.absoluteOffset(0.dp, absOffset),
                        showSecondaryText = false,
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
        titleText = "Item not found",
        descriptionText = "Try a more generic search term or try\n" +
                "looking for alternative products.",
    )
}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    MoboStoreTheme {
        SearchScreen(rememberNavController())
    }
}