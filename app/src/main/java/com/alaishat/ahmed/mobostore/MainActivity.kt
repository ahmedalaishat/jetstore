package com.alaishat.ahmed.mobostore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.ui.components.BottomBar
import com.alaishat.ahmed.mobostore.ui.components.DrawerContent
import com.alaishat.ahmed.mobostore.ui.components.modal.PaymentModal
import com.alaishat.ahmed.mobostore.ui.navigation.AppNavHost
import com.alaishat.ahmed.mobostore.ui.navigation.Screen
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.utils.advancedShadow
import com.alaishat.ahmed.mobostore.utils.animateClose
import com.alaishat.ahmed.mobostore.utils.animateOpen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val loggedIn = true // AHMED_TODO check if the user is logged in
            val startDestination = if (loggedIn) Screen.Home.route else Screen.OnBoarding.route
            val bottomBarState = rememberSaveable { (mutableStateOf(loggedIn)) }


            val navBackStackEntry by navController.currentBackStackEntryAsState()
//            val currentRoute = navBackStackEntry?.destination?.route
//            val isLoginStack = currentRoute == Screen.OnBoarding.route || currentRoute == Screen.Login.route
//            bottomBarState.value = !isLoginStack
            bottomBarState.value = when (navBackStackEntry?.destination?.route) {
                Screen.Home.route, Screen.Favorites.route,
                Screen.Profile.route, Screen.Basket.route -> true
                else -> false
            }

            val scope = rememberCoroutineScope()
            val configuration = LocalConfiguration.current


            val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

            val openDrawer = { scope.launch { drawerState.animateOpen() } }
            val closeDrawer = { scope.launch { drawerState.animateClose() } }

            var drawerOffset by remember { mutableStateOf(0f) }

            MoboStoreTheme {
                // AHMED_TODO refactor us
                val systemUiController = rememberSystemUiController()
                val barsColor = MaterialTheme.colorScheme.background
                val primaryColor = MaterialTheme.colorScheme.primary

                val offset = if (drawerOffset != 0f) drawerState.offset.value - drawerOffset else 0f

                val color =
                    if (offset > 1 || Screen.isLoginStack(navBackStackEntry?.destination?.route)) primaryColor
                    else if (modalBottomSheetState.targetValue != ModalBottomSheetValue.Hidden) Color(0xFf9B9B9B)
                    else barsColor
                val darkIcons = color != primaryColor

                SideEffect {
                    systemUiController.setSystemBarsColor(color = color, darkIcons = darkIcons)
                }
                // AHMED_TODO refactor us


                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    // AHMED_TODO move me to payment screen
                    ModalBottomSheetLayout(
                        sheetContent = { PaymentModal() },
                        sheetState = modalBottomSheetState,
                        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                    ) {
                        ModalNavigationDrawer(
                            drawerState = drawerState,
                            drawerContent = {
                                DrawerContent(navController, closeDrawer)
                            },
                            drawerContainerColor = Color.Transparent,
                            scrimColor = Color.Transparent,
                            gesturesEnabled = drawerState.isOpen,
                            drawerContentColor = MaterialTheme.colorScheme.onPrimary,
                        ) {
                            // AHMED_TODO refactor us
                            val rad =
                                if (drawerOffset != 0f) Dp((((drawerOffset - drawerState.offset.value) / drawerOffset)) * 32) else 0.dp
                            val scale =
                                if (drawerOffset != 0f) (drawerState.offset.value * 0.35f) / (drawerOffset) + .65f else 0f
//                            val offset = Dp((drawerState.offset.value - drawerWidth) / 3.5f)
                            val ratio = (drawerOffset - drawerState.offset.value) / (drawerOffset)
                            val scaffoldOffset =
                                if (ratio != Float.POSITIVE_INFINITY && ratio != Float.NEGATIVE_INFINITY)
                                    (ratio * 0.9f * configuration.screenWidthDp) - (ratio * configuration.screenWidthDp * 0.1f) else 0f
                            // AHMED_TODO refactor us

                            if (drawerOffset != 0f)
                                Image(
                                    painter = painterResource(id = R.drawable.bg_menu),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillBounds
                                )
                            Scaffold(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .scale(scale)
                                    .absoluteOffset(x = scaffoldOffset.dp)
                                    .advancedShadow(Color.White, 0.1f, rad, 1.dp, 24.dp, (-24).dp)
                                    .clip(RoundedCornerShape(rad)),
                                bottomBar = {
                                    BottomBar(navController = navController, bottomBarState)
                                },
                            ) { innerPadding ->
                                AppNavHost(
                                    navController = navController,
                                    modalBottomSheetState = modalBottomSheetState,
                                    openDrawer = openDrawer,
                                    scope = scope,
                                    startDestination = startDestination,
                                    innerPadding = innerPadding
                                )
                            }
                        }
                    }
                }
            }
            LaunchedEffect(Unit) {
                drawerOffset = drawerState.offset.value
            }
        }
    }
}