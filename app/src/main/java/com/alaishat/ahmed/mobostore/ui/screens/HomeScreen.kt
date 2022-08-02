package com.alaishat.ahmed.mobostore.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.HorizontalSpacer
import com.alaishat.ahmed.mobostore.ui.components.SearchField
import com.alaishat.ahmed.mobostore.ui.components.VerticalSpacer
import com.alaishat.ahmed.mobostore.ui.navigation.HomeTab
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.utils.animatePage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

/**
 * Created by Ahmed Al-Aishat on Aug/01/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */

@Composable
fun HomeScreen(navController: NavHostController) {
//    val ctx = LocalContext.current

    var search by remember { mutableStateOf("") }

    val systemUiController = rememberSystemUiController()
    val barsColor = MaterialTheme.colorScheme.background
    SideEffect {
        systemUiController.setSystemBarsColor(color = barsColor, darkIcons = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VerticalSpacer(height = 20.dp)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 50.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_menu), contentDescription = "")
            SearchField(
                value = search,
                onValueChange = { search = it },
                modifier = Modifier
                    .padding(start = 25.dp, end = 50.dp)
                    .fillMaxWidth(),
            )
        }
        VerticalSpacer(height = 55.dp)
        Text(
            text = "Order online\ncollect in store",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp)
        )
        VerticalSpacer(height = 55.dp)
        HomePager()

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
private fun HomePager() {
    val tabs = listOf(
        HomeTab.Wearable,
        HomeTab.Laptops,
        HomeTab.Phones,
        HomeTab.Drones,
    )
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        containerColor = MaterialTheme.colorScheme.background,
        edgePadding = 0.dp,
        divider = { TabRowDefaults.Divider(color = MaterialTheme.colorScheme.background) },
        modifier = Modifier.padding(start = 50.dp)
    ) {
        // Add tabs for all of our pages
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(text = stringResource(id = tab.nameId), style = MaterialTheme.typography.labelSmall) },
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
        HomeTabContent(tabs[tab])
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HomeTabContent(homeTab: HomeTab) {
    HorizontalPager(
        count = 5, // AHMED_TODO call api to get items depending on the current tab
        contentPadding = PaddingValues(start = 70.dp, end = 110.dp)
    ) { item ->
        // Calculate the absolute offset for the current page from the
        // scroll position. We use the absolute value which allows us to mirror
        // any effects for both directions
        val pageOffset = calculateCurrentOffsetForPage(item).absoluteValue
        HomeItem(pageOffset)
    }
}

@Composable
private fun HomeItem(pageOffset: Float) {
    Box(modifier = Modifier.animatePage(pageOffset)) {
        Card(
            modifier = Modifier
                .padding(top = 115.dp)
                .width(220.dp)
                .height(270.dp),
        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                VerticalSpacer(height = 140.dp)
                Text(text = "Apple Watch", style = MaterialTheme.typography.titleLarge, color = Color.Black)
                Text(
                    text = "Series 6 . Red",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                VerticalSpacer(height = 16.dp)
                Text(
                    text = "\$ 359",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.mask_group),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .absoluteOffset(y = 55.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    MoboStoreTheme {
        HomeScreen(rememberNavController())
    }
}