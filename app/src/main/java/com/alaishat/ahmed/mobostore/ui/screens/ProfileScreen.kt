package com.alaishat.ahmed.mobostore.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.HorizontalSpacer
import com.alaishat.ahmed.mobostore.ui.components.VerticalSpacer
import com.alaishat.ahmed.mobostore.ui.components.headers.AppHeader
import com.alaishat.ahmed.mobostore.ui.navigation.Screen
import com.alaishat.ahmed.mobostore.ui.theme.AppTypefaceTokens
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme

/**
 * Created by Ahmed Al-Aishat on Aug/01/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
fun ProfileScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppHeader("", onClickLeftIcon = { navController.popBackStack() })
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 30.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalSpacer(height = 40.dp)
            Text(
                text = "My profile",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 20.dp)
            )
            VerticalSpacer(height = 16.dp)
            Box(contentAlignment = Alignment.Center) {
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    VerticalSpacer(height = 60.dp)
                    Text(
                        text = "Ahmed Al-Aishat",
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    VerticalSpacer(height = 10.dp)
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_location),
                            contentDescription = "",
                            modifier = Modifier
                                .size(48.dp)
                                .padding(horizontal = 12.dp),
                            tint = Color.Unspecified
                        )
                        Text(
                            text = "Address: Ibn Asaker Road\n" +
                                    "M13 4GR\n" +
                                    "Damascus, Syria",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = AppTypefaceTokens.WeightRegular),
                            modifier = Modifier
                                .weight(1f),
                        )
                        HorizontalSpacer(width = 24.dp)
                    }
                    VerticalSpacer(height = 16.dp)
                }
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(75.dp)
                        .clip(CircleShape)
                        .align(Alignment.TopCenter)
                )
            }
            VerticalSpacer(height = 24.dp)
            ProfileListItem("Edit Profile") {
                navController.navigate(Screen.NoConnection.route)
            }
            VerticalSpacer(height = 24.dp)
            ProfileListItem("Shopping address") {}
            VerticalSpacer(height = 24.dp)
            ProfileListItem("Order history") {
                navController.navigate(Screen.OrderHistory.route)
            }
            VerticalSpacer(height = 24.dp)
            ProfileListItem("Cards") {}
            VerticalSpacer(height = 24.dp)
            ProfileListItem("Notifications") {}
            VerticalSpacer(height = 24.dp)
        }
    }
}

@Composable
private fun ProfileListItem(
    text: String,
    onClick: () -> Unit,
) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(start = 24.dp, top = 20.dp, bottom = 20.dp, end = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .weight(1f),
                style = MaterialTheme.typography.labelMedium,
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_left),
                contentDescription = null,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    MoboStoreTheme {
        ProfileScreen(rememberNavController())
    }
}