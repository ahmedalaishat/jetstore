package com.alaishat.ahmed.mobostore.ui.screens

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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.data.products.homeCategories
import com.alaishat.ahmed.mobostore.model.HomeCategory
import com.alaishat.ahmed.mobostore.ui.components.HorizontalSpacer
import com.alaishat.ahmed.mobostore.ui.components.ProductContent
import com.alaishat.ahmed.mobostore.ui.components.VerticalSpacer
import com.alaishat.ahmed.mobostore.ui.components.headers.SearchHeader
import com.alaishat.ahmed.mobostore.ui.navigation.Screen
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
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, openDrawer: () -> Any) {

    var search by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VerticalSpacer(height = 10.dp)
        SearchHeader(
            searchValue = search,
            onValueChange = { search = it },
            onClickLeftIcon = { openDrawer() },
            onClickInput = { navController.navigate(Screen.Search.route) }
        )
        VerticalSpacer(height = 55.dp)
        Text(
            text = "Order online\ncollect in store",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
        )
        VerticalSpacer(height = 55.dp)
        HomePager(navController)

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
                    text = "see more",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelMedium,
                )
                HorizontalSpacer(width = 8.dp)
                Image(painter = painterResource(id = R.drawable.ic_vector), contentDescription = null)
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HomePager(navController: NavController) {
//    val tabs = listOf(
//        HomeTab.Wearable,
//        HomeTab.Laptops,
//        HomeTab.Phones,
//        HomeTab.Drones,
//    )
    val tabs = homeCategories
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
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = {
                    Text(
                        text = stringResource(id = tab.category.tabName),
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.scrollToPage(index)
                    }
                },
                unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

    HorizontalPager(count = tabs.size, state = pagerState, userScrollEnabled = false) { tab ->
        HomeTabContent(tabs[tab], navController = navController)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HomeTabContent(homeCategory: HomeCategory, navController: NavController) {
    HorizontalPager(
        modifier = Modifier.padding(top = 50.dp),
        count = homeCategory.products.size,
        contentPadding = PaddingValues(start = 70.dp, end = 110.dp)
    ) { item ->
        // Calculate the absolute offset for the current page from the
        // scroll position. We use the absolute value which allows us to mirror
        // any effects for both directions
        val pageOffset = calculateCurrentOffsetForPage(item).absoluteValue
        ProductContent(
            homeCategory.products[item],
            modifier = Modifier.animatePage(pageOffset),
            onProductClicked = { navController.navigate(Screen.SingleItem.route) },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    MoboStoreTheme {
        HomeScreen(rememberNavController()) { }
    }
}