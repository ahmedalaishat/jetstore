package com.alaishat.ahmed.jetstore.ui.screens.login

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.alaishat.ahmed.jetstore.MainActivity
import com.alaishat.ahmed.jetstore.R
import com.alaishat.ahmed.jetstore.ui.components.VerticalSpacer
import com.alaishat.ahmed.jetstore.ui.components.buttons.PrimaryButton
import com.alaishat.ahmed.jetstore.ui.components.textfields.AppTextField
import com.alaishat.ahmed.jetstore.ui.components.texts.AppClickableText
import com.alaishat.ahmed.jetstore.ui.navigation.Screen
import com.alaishat.ahmed.jetstore.ui.theme.JetStoreTheme
import com.alaishat.ahmed.jetstore.utils.getActivity
import com.alaishat.ahmed.jetstore.utils.optStringResource
import kotlinx.coroutines.launch

/**
 * Created by Ahmed Al-Aishat on Jul/31/2022.
 * JetStore Project.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(LocalContext.current.getActivity<MainActivity>()!!)
) {
    val scope = rememberCoroutineScope()
    val login by rememberUpdatedState { loginViewModel.login() }

    val uiState by loginViewModel.uiState.collectAsState()

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val passwordTransformation =
        if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(mask = '*')

    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_login),
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
                text = stringResource(R.string.welcome_back),
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
                        text = stringResource(id = R.string.login),
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    VerticalSpacer(height = 45.dp)
                    AppTextField(
                        value = uiState.loginEmail,
                        onValueChange = { loginViewModel.onUpdateEmail(it) },
                        label = stringResource(R.string.email),
                        errorMsgId = optStringResource(id = uiState.loginEmailError),
                        labelIconId = R.drawable.ic_email,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
                    )
                    VerticalSpacer(height = 45.dp)
                    AppTextField(
                        value = uiState.loginPassword,
                        onValueChange = { loginViewModel.onUpdatePassword(it) },
                        label = stringResource(R.string.passowrd),
                        errorMsgId = optStringResource(id = uiState.loginPasswordError),
                        labelIconId = R.drawable.ic_password,
                        visualTransformation = passwordTransformation,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Go
                        ),
                        keyboardActions = KeyboardActions(
                            onGo = {
                                keyboardController?.hide()
//                                navigate(ctx, navController, keyboardController)
                                login()
                            }),
                        trailingIcon = {
                            PasswordTrailingIcon(passwordVisible) {
                                passwordVisible = !passwordVisible
                            }
                        },
                    )
                    VerticalSpacer(height = 24.dp)
                    AppClickableText(
                        text = stringResource(R.string.forgot_password),
                        onClick = {},
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
                VerticalSpacer(height = 62.dp)
                PrimaryButton(
                    onClick = {
                        scope.launch {
//                            loading = true
//                            delay(2000)
//                            loading = false
                            keyboardController?.hide()
//                            navigate(ctx, navController, keyboardController)
                            login()
                        }
                    },
                    text = stringResource(R.string.login),
                    loading = uiState.isLoading
                )
                VerticalSpacer(height = 20.dp)
                AppClickableText(text = stringResource(R.string.create_account), onClick = {})
                VerticalSpacer(height = 20.dp)
            }
        }
    }
}

@Composable
private fun PasswordTrailingIcon(passwordVisible: Boolean, onClick: (Int) -> Unit) {
    val description = if (passwordVisible) R.string.hide else R.string.show
    AppClickableText(
        text = stringResource(description),
        onClick = onClick,
        style = MaterialTheme.typography.labelSmall,
    )
}

private fun gotoHome(navController: NavController) {
    val options = NavOptions.Builder().setPopUpTo(Screen.Login.route, true).build()
    navController.navigate(Screen.Home.route, options)
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    JetStoreTheme {
        LoginScreen(hiltViewModel())
    }
}