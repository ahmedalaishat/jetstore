package com.alaishat.ahmed.mobostore.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.AppButton
import com.alaishat.ahmed.mobostore.ui.components.SearchField
import com.alaishat.ahmed.mobostore.ui.components.VerticalSpacer
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.utils.advancedShadow
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Created by Ahmed Al-Aishat on Aug/01/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */

@OptIn(ExperimentalMaterial3Api::class)
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
            .padding(start = 50.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VerticalSpacer(height = 50.dp)
        Row(verticalAlignment = Alignment.CenterVertically) {
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
            modifier = Modifier.fillMaxWidth()
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