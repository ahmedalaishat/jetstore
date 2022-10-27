package com.alaishat.ahmed.jetstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.jetstore.ui.components.JetStoreSnackbarHost
import com.alaishat.ahmed.jetstore.ui.components.navigation.BottomBar
import com.alaishat.ahmed.jetstore.ui.components.navigation.DrawerContent
import com.alaishat.ahmed.jetstore.ui.navigation.AppNavHost
import com.alaishat.ahmed.jetstore.ui.navigation.Screen
import com.alaishat.ahmed.jetstore.ui.screens.login.LoginViewModel
import com.alaishat.ahmed.jetstore.ui.theme.JetStoreTheme
import com.alaishat.ahmed.jetstore.utils.advancedShadow
import com.alaishat.ahmed.jetstore.utils.animateClose
import com.alaishat.ahmed.jetstore.utils.animateOpen
import com.alaishat.ahmed.jetstore.utils.barsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()
            val systemUiController = rememberSystemUiController()
            val configuration = LocalConfiguration.current
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

            val snackbarHostState = remember { SnackbarHostState() }

            val authState by loginViewModel.uiState.collectAsState()
            val loggedIn = authState.isLoggedIn() // AHMED_TODO check if the user is logged in
            val showBottomBar = rememberSaveable { (mutableStateOf(loggedIn)) }
            var drawerInitOffset by remember { mutableStateOf(0f) }

            showBottomBar.value = Screen.isBottomBarScreen(currentRoute)
            val scaffoldPadding = if (!loggedIn) PaddingValues() else WindowInsets.barsPadding

            val offsetRatio = if (drawerInitOffset == 0f) 0f
            else (drawerInitOffset - drawerState.offset.value) / drawerInitOffset
            val scaffoldRadius = Dp(32 * offsetRatio)
            val scaffoldScale = 1f - offsetRatio * .35f
            val scaffoldOffset = (offsetRatio * configuration.screenWidthDp * .8f)

            SideEffect {
                val darkIcons = offsetRatio < .1f && !Screen.isLoginScreen(currentRoute)
                systemUiController.setSystemBarsColor(color = Color.Transparent, darkIcons = darkIcons)
            }

            val openDrawer by rememberUpdatedState { scope.launch { drawerState.animateOpen() } }
            val closeDrawer by rememberUpdatedState { scope.launch { drawerState.animateClose() } }
            val logout by rememberUpdatedState { loginViewModel.logout() }

            JetStoreTheme {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            DrawerContent(navController, closeDrawer, logout)
                        },
                        drawerContainerColor = Color.Transparent,
                        scrimColor = Color.Transparent,
                        gesturesEnabled = drawerState.isOpen,
                        drawerContentColor = MaterialTheme.colorScheme.onPrimary,
                    ) {
                        if (drawerInitOffset != 0f)
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(id = R.drawable.bg_menu),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds
                            )
                        Scaffold(
                            modifier = Modifier
                                .fillMaxSize()
                                .scale(scaffoldScale)
                                .absoluteOffset(x = scaffoldOffset.dp)
                                .advancedShadow(Color.White, 0.1f, scaffoldRadius, 1.dp, 24.dp, (-24).dp)
                                .clip(RoundedCornerShape(scaffoldRadius))
                                .background(MaterialTheme.colorScheme.background)
                                .padding(scaffoldPadding),
                            bottomBar = {
                                BottomBar(navController = navController, showBottomBar)
                            },
                            snackbarHost = { JetStoreSnackbarHost(hostState = snackbarHostState) }
                        ) { innerPadding ->
                            AppNavHost(
                                navController = navController,
                                openDrawer = openDrawer,
                                scope = scope,
                                startDestination = Screen.getStartDestination(loggedIn).route,
                                innerPadding = innerPadding,
                                snackbarHostState = snackbarHostState,
                            )
                        }
                    }
                }
            }
            LaunchedEffect(Unit) {
                drawerInitOffset = drawerState.offset.value
            }
        }
    }
}