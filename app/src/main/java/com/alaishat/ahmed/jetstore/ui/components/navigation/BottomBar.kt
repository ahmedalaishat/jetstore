package com.alaishat.ahmed.jetstore.ui.components.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.jetstore.ui.navigation.Screen
import com.alaishat.ahmed.jetstore.ui.theme.JetStoreTheme
import com.alaishat.ahmed.jetstore.utils.bottomBarNavigate

/**
 * Created by Ahmed Al-Aishat on Aug/01/2022.
 * JetStore Project.
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
                contentColor = MaterialTheme.colorScheme.onBackground,

                ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        icon = {
                            Icon(painterResource(screen.getDrawableId(selected)!!), contentDescription = null)
                        },
                        selected = selected,
                        onClick = {
                            navController.bottomBarNavigate(screen.route)
                        },
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
    JetStoreTheme {
        BottomBar(rememberNavController(), rememberSaveable { mutableStateOf(false) })
    }
}