package com.alaishat.ahmed.mobostore.utils

import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.alaishat.ahmed.mobostore.ui.navigation.Screen

/**
 * Created by Ahmed Al-Aishat on Aug/21/2022.
 * Mobo Store Project.
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
//
//@Composable
//inline fun <reified T : ViewModel> NavBackStackEntry?.viewModel(): T? = this?.let {
//    hiltViewModel(viewModelStoreOwner = it)
//}

@Composable
inline fun <reified T : ViewModel> NavController.previousHiltViewModel(): T {
    val viewModelStoreOwner = previousBackStackEntry ?: checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }
    return androidx.hilt.navigation.compose.hiltViewModel(
        viewModelStoreOwner = viewModelStoreOwner
    )
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