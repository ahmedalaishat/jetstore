package com.alaishat.ahmed.mobostore.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.theme.DividerColor
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.ui.theme.TextFieldLabelColor
import com.alaishat.ahmed.mobostore.ui.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Created by Ahmed Al-Aishat on Jul/31/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

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
    Image(
        painter = painterResource(id = R.drawable.login),
        contentDescription = "",
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 50.dp),
            lineHeight = 50.sp,
            text = "Welcome back", color = Color.White,
            fontSize = 50.sp
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp, 20.dp))
                .background(White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
            )
            Text(
                text = "Login", color = Color.Black, modifier = Modifier
                    .fillMaxWidth()
                    .absoluteOffset(50.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "email",
                        tint = TextFieldLabelColor
                    )
                    Text(text = "Email", color = TextFieldLabelColor, modifier = Modifier.padding(start = 11.dp))
                }
                MyTextField(
                    value = email,
                    onValueChange = { email = it },
//                    label = { Text(text = "Email") },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        focusedIndicatorColor = DividerColor,
                        unfocusedIndicatorColor = DividerColor,
                        containerColor = Color.White,
                        cursorColor = TextFieldLabelColor,
                        selectionColors = TextSelectionColors(Color.LightGray, Color.LightGray),
                    ),
                    modifier = Modifier.fillMaxWidth(),
                )
                Row(modifier = Modifier.padding(top = 40.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "password",
                        tint = TextFieldLabelColor
                    )
                    Text(text = "Password", color = TextFieldLabelColor, modifier = Modifier.padding(start = 11.dp))
                }
                MyTextField(
                    value = password, onValueChange = { password = it },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(
                        mask = '*'
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        focusedIndicatorColor = DividerColor,
                        unfocusedIndicatorColor = DividerColor,
                        containerColor = Color.White,
                        cursorColor = TextFieldLabelColor,
                        selectionColors = TextSelectionColors(Color.LightGray, Color.LightGray),
                    ),
                    trailingIcon = {
                        // Please provide localized description for accessibility services
                        val description = if (passwordVisible) "Hide" else "Show"
                        ClickableText(
                            text = AnnotatedString(
                                text = description,
                                spanStyle = SpanStyle(color = MaterialTheme.colorScheme.secondary)
                            ),
                            onClick = {
                                passwordVisible = !passwordVisible
                            },
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                )

                ClickableText(
                    text = AnnotatedString(
                        text = "Forgot password?",
                        spanStyle = SpanStyle(color = MaterialTheme.colorScheme.secondary)
                    ),
                    modifier = Modifier.padding(top = 24.dp),
                    onClick = {

                    },
                )
            }
//            Spacer(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp)
//            )
            Button(
                onClick = { Toast.makeText(ctx, "Logged In", Toast.LENGTH_LONG).show() },
                modifier = Modifier
                    .padding(top = 50.dp)
                    .width(314.dp)
                    .height(70.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text(text = "Login", modifier = Modifier.background(MaterialTheme.colorScheme.secondary))
            }
            ClickableText(
                text = AnnotatedString(
                    text = "Create account",
                    spanStyle = SpanStyle(color = MaterialTheme.colorScheme.secondary)
                ),
                onClick = {
                },
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults.filledShape,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors()
) {
    // If color is not provided via the text style, use content color as a default
    val textColor = textStyle.color.takeOrElse {
        colors.textColor(enabled).value
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    CompositionLocalProvider(LocalTextSelectionColors provides colors.selectionColors) {
        @OptIn(ExperimentalMaterial3Api::class)
        BasicTextField(
            value = value,
            modifier = modifier
                .background(colors.containerColor(enabled).value, shape)
                .indicatorLine(false, isError, interactionSource, colors)
                .defaultMinSize(
                    minWidth = TextFieldDefaults.MinWidth,
                    minHeight = 48.dp
                ),
            onValueChange = onValueChange,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = mergedTextStyle,
            cursorBrush = SolidColor(colors.cursorColor(isError).value),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,
            singleLine = singleLine,
            maxLines = maxLines,
            decorationBox = @Composable { innerTextField ->
                // places leading icon, text field with label and placeholder, trailing icon
                TextFieldDefaults.TextFieldDecorationBox(
                    value = value,
                    visualTransformation = visualTransformation,
                    innerTextField = innerTextField,
                    placeholder = placeholder,
                    label = label,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    singleLine = singleLine,
                    enabled = enabled,
                    isError = isError,
                    interactionSource = interactionSource,
                    colors = colors,
                    contentPadding = PaddingValues(vertical = 10.dp, horizontal = 4.dp)
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    MoboStoreTheme {
        LoginScreen()
    }
}