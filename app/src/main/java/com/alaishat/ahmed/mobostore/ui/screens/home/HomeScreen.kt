package com.alaishat.ahmed.mobostore.ui.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.model.HomeCategory
import com.alaishat.ahmed.mobostore.model.Product
import com.alaishat.ahmed.mobostore.ui.components.FullScreenLoading
import com.alaishat.ahmed.mobostore.ui.components.HorizontalSpacer
import com.alaishat.ahmed.mobostore.ui.components.ProductContent
import com.alaishat.ahmed.mobostore.ui.components.VerticalSpacer
import com.alaishat.ahmed.mobostore.ui.components.content.LoadingContent
import com.alaishat.ahmed.mobostore.ui.components.headers.SearchHeader
import com.alaishat.ahmed.mobostore.ui.navigation.Screen
import com.alaishat.ahmed.mobostore.ui.screens.NoConnectionScreen
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.utils.animatePage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

/**
 * Created by Ahmed Al-Aishat on Aug/01/2022.
 * Mobo Store Project.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    openDrawer: () -> Any,
    snackbarHostState: SnackbarHostState,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    // UiState of the HomeScreen
    val uiState by homeViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        VerticalSpacer(height = 10.dp)
        SearchHeader(
            searchValue = "",
            onValueChange = {  },
            onClickLeftIcon = { openDrawer() },
            onClickInput = {
                homeViewModel.searchOpened(true)
                navController.navigate(Screen.Search.route)
            },
        )
        VerticalSpacer(height = 55.dp)

        LoadingContent(
            empty = uiState.homeCategories.flatMap { it.products }.isEmpty() && uiState.isLoading,
            emptyContent = { FullScreenLoading() },
            loading = uiState.isLoading,
            onRefresh = { },
            content = {
                if (uiState.homeCategories.flatMap { it.products }.isEmpty()) {
                    if (uiState.errorMessages.isEmpty()) {
                        // if there are no products, and no error, let the user refresh manually
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            NoConnectionScreen { homeViewModel.refreshProducts() }
                        }
                    } else {
                        // there's currently an error showing, don't show any content
                        Box(Modifier.fillMaxSize()) { /* empty screen */ }
                    }
                } else {
                    HomeContent(navController, uiState)
                }
            }
        )
    }

    // Process one error message at a time and show them as Snackbars in the UI
    if (uiState.errorMessages.isNotEmpty()) {
        // Remember the errorMessage to display on the screen
        val errorMessage = remember(uiState) { uiState.errorMessages[0] }

        // Get the text to show on the message from resources
        val errorMessageText: String = stringResource(errorMessage.messageId)
        val retryMessageText = stringResource(id = R.string.retry)

        // If refreshProducts or errorShown change while the LaunchedEffect is running,
        // don't restart the effect and use the latest lambda values.
        val onRefreshProductState by rememberUpdatedState { homeViewModel.refreshProducts() }
        val onErrorDismissState by rememberUpdatedState { id: Long -> homeViewModel.errorShown(id) }

        // Effect running in a coroutine that displays the Snackbar on the screen
        // If there's a change to errorMessageText, retryMessageText or snackbarHostState,
        // the previous effect will be cancelled and a new one will start with the new values
        LaunchedEffect(errorMessageText, retryMessageText, snackbarHostState) {
            val snackbarResult = snackbarHostState.showSnackbar(
                message = errorMessageText,
                actionLabel = retryMessageText
            )
            if (snackbarResult == SnackbarResult.ActionPerformed) {
                onRefreshProductState()
            }
            // Once the message is displayed and dismissed, notify the ViewModel
            onErrorDismissState(errorMessage.id)
        }
    }
}

@Composable
private fun HomeContent(
    navController: NavHostController,
    uiState: HomeUiState
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.Home_title),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
        )
        VerticalSpacer(height = 55.dp)
        HomePager(navController, uiState.homeCategories)
        Box(
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                Modifier.clickable { },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.absoluteOffset(y = (-3).dp),
                    text = stringResource(R.string.see_more),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelMedium,
                )
                HorizontalSpacer(width = 8.dp)
                Image(
                    painter = painterResource(id = R.drawable.ic_vector),
                    contentDescription = null
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HomePager(navController: NavController, homeCategories: List<HomeCategory>) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        containerColor = MaterialTheme.colorScheme.background,
        edgePadding = 0.dp,
        divider = { TabRowDefaults.Divider(color = MaterialTheme.colorScheme.background) },
        modifier = Modifier.padding(start = 24.dp)
    ) {
        // Add tabs for all of our pages
        homeCategories.forEachIndexed { position, HomeCategory ->
            Tab(
                text = {
                    Text(
                        text = stringResource(id = HomeCategory.category.tabName),
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                selected = pagerState.currentPage == position,
                onClick = {
                    coroutineScope.launch {
                        pagerState.scrollToPage(position)
                    }
                },
                unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

    HorizontalPager(
        count = homeCategories.size,
        state = pagerState,
        userScrollEnabled = false
    ) { position ->
        HomeTabContent(homeCategories[position].products, navController = navController)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HomeTabContent(products: List<Product>, navController: NavController) {
    HorizontalPager(
        modifier = Modifier.padding(top = 50.dp),
        count = products.size,
        contentPadding = PaddingValues(start = 70.dp, end = 110.dp)
    ) { position ->
        // Calculate the absolute offset for the current page from the
        // scroll position. We use the absolute value which allows us to mirror
        // any effects for both directions
        val pageOffset = calculateCurrentOffsetForPage(position).absoluteValue
        ProductContent(
            product = products[position],
            modifier = Modifier.animatePage(pageOffset),
            onProductClicked = {
                navController.navigate(route = "${Screen.Product.route}/${products[position].id}")
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    MoboStoreTheme {
        HomeScreen(rememberNavController(), {}, SnackbarHostState())
    }
}