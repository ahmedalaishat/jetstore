package com.alaishat.ahmed.mobostore.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.alaishat.ahmed.mobostore.ui.navigation.Screen
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.utils.bottomBarNavigate

/**
 * Created by Ahmed Al-Aishat on Aug/01/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
fun BottomBar(
    navController: NavController,
    bottomBarState: MutableState<Boolean>,
) {
    if (!bottomBarState.value) return

    val items = Screen.bottomBarScreens()

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.background,

                ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painterResource(if (selected) screen.selectedDrawableId!! else screen.drawableId!!),
                                contentDescription = null,
                            )
                        },
//                label = { Text(stringResource(screen.resourceId)) },
                        selected = selected,
                        onClick = {
                            navController.bottomBarNavigate(screen.route)
                        },
//                modifier = Modifier.background(MaterialTheme.colorScheme.background),
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.background
                        )
                    )
                }
            }
        })
}

@Composable
fun BottomBarPreview() {
    MoboStoreTheme {
//        BottomBar(rememberNavController())
    }
}