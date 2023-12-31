package com.alaishat.ahmed.jetstore.ui.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.jetstore.R
import com.alaishat.ahmed.jetstore.model.HomeCategory
import com.alaishat.ahmed.jetstore.ui.components.FullScreenLoading
import com.alaishat.ahmed.jetstore.ui.components.HorizontalSpacer
import com.alaishat.ahmed.jetstore.ui.components.ProductContent
import com.alaishat.ahmed.jetstore.ui.components.VerticalSpacer
import com.alaishat.ahmed.jetstore.ui.components.content.LoadingContent
import com.alaishat.ahmed.jetstore.ui.components.headers.SearchHeader
import com.alaishat.ahmed.jetstore.ui.navigation.Screen
import com.alaishat.ahmed.jetstore.ui.screens.NoConnectionScreen
import com.alaishat.ahmed.jetstore.ui.screens.home.HomeViewModel.OneShotEvent.NetworkError
import com.alaishat.ahmed.jetstore.ui.theme.JetStoreTheme
import com.alaishat.ahmed.jetstore.utils.animatePage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

/**
 * Created by Ahmed Al-Aishat on Aug/01/2022.
 * JetStore Project.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    openDrawer: () -> Any,
    snackbarHostState: SnackbarHostState,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    // UiState of the HomeScreen
    val uiState by homeViewModel.uiState.collectAsState()

    val onRefreshState by rememberUpdatedState { homeViewModel.refreshProducts() }

    LaunchedEffect(snackbarHostState) {
        homeViewModel.oneShotEvents
            .onEach {
                val (actionLabel, action) =
                    when (it) {
                        NetworkError -> Pair(R.string.retry, onRefreshState)
                    }
                val snackbarResult = snackbarHostState.showSnackbar(
                    message = context.getString(it.message!!),
                    actionLabel = context.getString(actionLabel)
                )
                if (snackbarResult == SnackbarResult.ActionPerformed) {
                    action()
                }
            }.collect()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        VerticalSpacer(height = 10.dp)
        SearchHeader(
            searchValue = "",
            onValueChange = { },
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
            onRefresh = { homeViewModel.refreshProducts() },
            content = {
                if (uiState.homeCategories.flatMap { it.products }.isEmpty()) {
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        NoConnectionScreen { homeViewModel.refreshProducts() }
                    }
                } else {
                    HomeContent(navController, uiState)
                }
            }
        )
    }

//    // Process one error message at a time and show them as Snackbars in the UI
//    if (uiState.errorMessages.isNotEmpty()) {
//        // Remember the errorMessage to display on the screen
//        val errorMessage = remember(uiState) { uiState.errorMessages[0] }
//
//        // Get the text to show on the message from resources
//        val errorMessageText: String = stringResource(errorMessage.messageId)
//        val retryMessageText = stringResource(id = R.string.retry)
//
//        // If refreshProducts or errorShown change while the LaunchedEffect is running,
//        // don't restart the effect and use the latest lambda values.
//        val onRefreshProductState by rememberUpdatedState { homeViewModel.refreshProducts() }
//        val onErrorDismissState by rememberUpdatedState { id: Long -> homeViewModel.errorShown(id) }
//
//        // Effect running in a coroutine that displays the Snackbar on the screen
//        // If there's a change to errorMessageText, retryMessageText or snackbarHostState,
//        // the previous effect will be cancelled and a new one will start with the new values
//        LaunchedEffect(errorMessageText, retryMessageText, snackbarHostState) {
//            val snackbarResult = snackbarHostState.showSnackbar(
//                message = errorMessageText,
//                actionLabel = retryMessageText
//            )
//            if (snackbarResult == SnackbarResult.ActionPerformed) {
//                onRefreshProductState()
//            }
//            // Once the message is displayed and dismissed, notify the ViewModel
//            onErrorDismissState(errorMessage.id)
//        }
//    }
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
        HomeTabContent(homeCategories[position], navController = navController)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HomeTabContent(category: HomeCategory, navController: NavController) {
    val products = category.products
    val tab = stringResource(id = category.category.tabName)

    if (products.isEmpty())
        Text(
            text = "No $tab",
            style = MaterialTheme.typography.labelSmall,
        )
    else
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
    JetStoreTheme {
        HomeScreen(rememberNavController(), {}, SnackbarHostState())
    }
}