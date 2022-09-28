package com.alaishat.ahmed.mobostore.utils

import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavController
import com.alaishat.ahmed.mobostore.ui.navigation.Screen

/**
 * Created by Ahmed Al-Aishat on Aug/21/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
fun NavController.bottomBarNavigate(
    route: String,
    bottomBarScreens: List<Screen> = Screen.bottomBarScreens()
) {
    navigate(route) {
        val navigationRoutes = bottomBarScreens.map(Screen::route)
        val firstBottomBarDestination = backQueue
            .firstOrNull { navigationRoutes.contains(it.destination.route) }?.destination
        // remove all navigation items from the stack
        // so only the currently selected screen remains in the stack
        if (firstBottomBarDestination != null) {
            popUpTo(firstBottomBarDestination.id) {
//                                        inclusive = true
                saveState = true
            }
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
}

@OptIn(ExperimentalMaterial3Api::class)
suspend fun DrawerState.animateClose() {
    if (isOpen)
        animateTo(DrawerValue.Closed, FloatTweenSpec(100, 0, LinearOutSlowInEasing))
}

@OptIn(ExperimentalMaterial3Api::class)
suspend fun DrawerState.animateOpen() {
    animateTo(DrawerValue.Open, FloatTweenSpec(100, 0, LinearOutSlowInEasing))
}