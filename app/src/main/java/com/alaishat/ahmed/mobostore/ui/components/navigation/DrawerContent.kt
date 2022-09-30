package com.alaishat.ahmed.mobostore.ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.HorizontalSpacer
import com.alaishat.ahmed.mobostore.ui.navigation.Screen
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.utils.bottomBarNavigate
import com.alaishat.ahmed.mobostore.utils.silentClickable

/**
 * Created by Ahmed Al-Aishat on Aug/20/2022.
 * Mobo Store Project.
 */
@Composable
fun DrawerContent(
    navController: NavController,
    closeDrawer: () -> Any,
    logout: () -> Any,
    menuItems: List<Screen> = Screen.drawerMenuScreens(),
    background: Color = Color.Transparent,
    width: Dp = 250.dp,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .requiredWidth(width)
                .fillMaxHeight()
                .padding(end = 10.dp)
                .silentClickable { closeDrawer() }
                .background(background)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            LazyColumn(Modifier.weight(5f)) {
                items(count = menuItems.size) { index ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.bottomBarNavigate(menuItems[index].route)
                                closeDrawer()
                            }
                            .padding(30.dp)) {
                        Icon(painter = painterResource(id = menuItems[index].drawableId!!), contentDescription = "")
                        HorizontalSpacer(width = 10.dp)
                        Text(text = menuItems[index].name)
                    }
                    if (index < menuItems.size - 1) Divider(
                        thickness = 0.25.dp,
                        modifier = Modifier.padding(start = 65.dp),
                        color = Color(0xFFF4F4F8)
                    )
                }
            }
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                Row(
                    Modifier
                        .clickable {
                            logout()
                            navigate(navController)
                            closeDrawer()
                        }
                        .padding(horizontal = 30.dp, vertical = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_logout), contentDescription = "")
                    HorizontalSpacer(width = 10.dp)
                    Text(text = "Sign out")
                }
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .silentClickable { closeDrawer() },
        )
    }
}

fun navigate(navController: NavController) {
    val opt = NavOptions
        .Builder()
        .setPopUpTo(Screen.Home.route, true)
        .build()
    navController.navigate(Screen.Login.route, opt)
}

@Composable
fun DrawerContentPreview() {
    MoboStoreTheme {
        DrawerContent(navController = rememberNavController(), closeDrawer = { }, logout = {})
    }
}