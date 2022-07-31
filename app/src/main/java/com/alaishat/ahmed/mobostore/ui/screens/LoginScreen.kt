package com.alaishat.ahmed.mobostore.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.AppButton
import com.alaishat.ahmed.mobostore.ui.components.AppTextField
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.ui.theme.White
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
    val keyboardController = LocalSoftwareKeyboardController.current

    val ctx = LocalContext.current
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.colorScheme.secondary
    SideEffect {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're in light theme
        systemUiController.setStatusBarColor(
            color = Color(color.toArgb()),
            darkIcons = false
        )
        systemUiController.setNavigationBarColor(
            color = Color.White,
            darkIcons = false
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "",
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 50.dp, end = 50.dp, top = 100.dp, bottom = 60.dp),
                text = "Welcome back",
                color = White,
                style = MaterialTheme.typography.displayLarge
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .clip(RoundedCornerShape(20.dp, 20.dp))
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 50.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Login",
                        color = Color.Black,
                        modifier = Modifier
                            .padding(top = 36.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    AppTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Email",
                        labelIconId = R.drawable.ic_email,
                        modifier = Modifier
                            .padding(top = 45.dp)
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
                    )
                    AppTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = "Password",
                        labelIconId = R.drawable.ic_password,
                        modifier = Modifier
                            .padding(top = 45.dp)
                            .fillMaxWidth(),
                        visualTransformation = if (passwordVisible) VisualTransformation.None
                        else PasswordVisualTransformation(mask = '*'),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Go
                        ),
                        keyboardActions = KeyboardActions(onGo = {
                            keyboardController?.hide()
                            login(ctx)
                        }),
                        trailingIcon = {
                            val description = if (passwordVisible) "Hide" else "Show"
                            ClickableText(
                                text = AnnotatedString(
                                    text = description,
                                    spanStyle = MaterialTheme.typography.labelMedium.toSpanStyle()
                                        .copy(color = MaterialTheme.colorScheme.secondary)
                                ),
                                onClick = { passwordVisible = !passwordVisible },
                            )
                        },
                    )
                    ClickableText(
                        text = AnnotatedString(
                            text = "Forgot password?",
                            spanStyle = MaterialTheme.typography.labelSmall.toSpanStyle()
                                .copy(color = MaterialTheme.colorScheme.secondary)
                        ),
                        modifier = Modifier.padding(top = 24.dp),
                        onClick = {

                        },
                    )
                }
                AppButton(
                    modifier = Modifier.padding(top = 62.dp),
                    onClick = {
                        keyboardController?.hide()
                        login(ctx)
                    },
                    text = "Login",
                )
                ClickableText(
                    text = AnnotatedString(
                        text = "Create account",
                        spanStyle = MaterialTheme.typography.labelMedium.toSpanStyle()
                            .copy(color = MaterialTheme.colorScheme.secondary),
                    ),
                    onClick = {
                    },
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
    }
}

private fun login(ctx: Context) {
    Toast.makeText(ctx, "Logged In", Toast.LENGTH_LONG).show()
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    MoboStoreTheme {
        LoginScreen(rememberNavController())
    }
}