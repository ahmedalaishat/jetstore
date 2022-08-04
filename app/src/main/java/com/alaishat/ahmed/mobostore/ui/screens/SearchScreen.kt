package com.alaishat.ahmed.mobostore.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.*
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.utils.header

/**
 * Created by Ahmed Al-Aishat on Aug/03/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
fun SearchScreen(navController: NavHostController) {

    var search by remember { mutableStateOf("") }

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
            val count = 5
            header {
                SearchHeader(navController, search, { search = it }, count)
            }
            items(count) { item ->
                val offset = if (item % 2 == 0) 0.dp else 50.dp
                Product(Modifier.padding(top = offset, start = 35.dp, end = 35.dp))
            }
            if (count == 0)
                item(span = { GridItemSpan(this.maxLineSpan) }, content = { NoItems() })
        }
    }
}

@Composable
private fun SearchHeader(
    navController: NavHostController,
    search: String,
    onSearch: (String) -> Unit,
    count: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(bounded = true, radius = 24.dp),
                        onClick = { navController.popBackStack() })
                    .padding(horizontal = 12.dp)
            )
            HorizontalSpacer(width = 12.dp)
            SearchField(
                value = search,
                onValueChange = onSearch,
                modifier = Modifier
                    .padding(end = 50.dp)
                    .fillMaxWidth(),
            )
        }
        if (count > 0)
            Text(
                text = "Found  6 results",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center
            )
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