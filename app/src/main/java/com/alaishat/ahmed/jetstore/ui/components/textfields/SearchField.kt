package com.alaishat.ahmed.jetstore.ui.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alaishat.ahmed.jetstore.R

/**
 * Created by Ahmed Al-Aishat on Aug/01/2022.
 * JetStore Project.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    focusRequester: FocusRequester = FocusRequester(),
) {
    val primary = MaterialTheme.colorScheme.primary
    val outline = MaterialTheme.colorScheme.outline

    var focused by remember { mutableStateOf(true) }

    TextField(
        value = value,
        enabled = enabled,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = "Search")
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.background,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        modifier = modifier
            .border(1.dp, if (focused) primary else outline, CircleShape)
            .clip(CircleShape)
            .background(Red)
            .clickable { onClick() }
            .focusRequester(focusRequester)
            .onFocusChanged {
                focused = it.isFocused
            },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "",
                tint = Color.Unspecified
            )
        },
        textStyle = MaterialTheme.typography.labelMedium,
        singleLine = true
    )
}