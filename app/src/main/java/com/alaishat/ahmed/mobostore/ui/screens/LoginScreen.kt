package com.alaishat.ahmed.mobostore.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.AppButton
import com.alaishat.ahmed.mobostore.ui.components.AppClickableText
import com.alaishat.ahmed.mobostore.ui.components.AppTextField
import com.alaishat.ahmed.mobostore.ui.components.VerticalSpacer
import com.alaishat.ahmed.mobostore.ui.navigation.Screen
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Created by Ahmed Al-Aishat on Jul/31/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val passwordTransformation =
        if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(mask = '*')

    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current

    val systemUiController = rememberSystemUiController()
    val statusBarColor = MaterialTheme.colorScheme.primary
    val navigationBarColor = MaterialTheme.colorScheme.surface
    SideEffect {
        systemUiController.setStatusBarColor(color = statusBarColor, darkIcons = false)
        systemUiController.setNavigationBarColor(color = navigationBarColor, darkIcons = false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "",
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            VerticalSpacer(height = 100.dp)
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                text = "Welcome back",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displayLarge
            )
            VerticalSpacer(height = 60.dp)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .clip(RoundedCornerShape(20.dp, 20.dp))
                    .background(MaterialTheme.colorScheme.surface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 50.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    VerticalSpacer(height = 36.dp)
                    Text(
                        text = "Login",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    VerticalSpacer(height = 45.dp)
                    AppTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Email",
                        labelIconId = R.drawable.ic_email,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
                    )
                    VerticalSpacer(height = 45.dp)
                    AppTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = "Password",
                        labelIconId = R.drawable.ic_password,
                        visualTransformation = passwordTransformation,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Go
                        ),
                        keyboardActions = KeyboardActions(
                            onGo = { login(ctx, navController, keyboardController) }),
                        trailingIcon = {
                            PasswordTrailingIcon(passwordVisible) {
                                passwordVisible = !passwordVisible
                            }
                        },
                    )
                    VerticalSpacer(height = 24.dp)
                    AppClickableText(
                        text = "Forgot password?",
                        onClick = {},
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
                VerticalSpacer(height = 62.dp)
                AppButton(
                    onClick = { login(ctx, navController, keyboardController) },
                    text = "Login",
                )
                VerticalSpacer(height = 20.dp)
                AppClickableText(text = "Create account", onClick = {})
                VerticalSpacer(height = 20.dp)
            }
        }
    }
}

@Composable
private fun PasswordTrailingIcon(passwordVisible: Boolean, onClick: (Int) -> Unit) {
    val description = if (passwordVisible) "Hide" else "Show"
    AppClickableText(
        text = description,
        onClick = onClick,
        style = MaterialTheme.typography.labelSmall,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
private fun login(ctx: Context, navController: NavController, keyboardController: SoftwareKeyboardController?) {
    keyboardController?.hide()
    Toast.makeText(ctx, "Logged In", Toast.LENGTH_LONG).show()
    gotoHome(navController)
}

fun gotoHome(navController: NavController) {
    val options = NavOptions.Builder().setPopUpTo(Screen.Login.route, true).build()
    navController.navigate(Screen.Home.route, options)
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    MoboStoreTheme {
        LoginScreen(rememberNavController())
    }
}