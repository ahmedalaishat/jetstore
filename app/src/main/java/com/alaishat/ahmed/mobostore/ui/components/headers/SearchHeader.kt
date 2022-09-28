package com.alaishat.ahmed.mobostore.ui.components.headers

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.buttons.IconButton
import com.alaishat.ahmed.mobostore.ui.components.textfields.SearchField

/**
 * Created by Ahmed Al-Aishat on Aug/21/2022.
 * Mobo Store Project.
 */
@Composable
fun SearchHeader(
    searchValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    inputEnabled: Boolean = false,
    onClickInput: () -> Unit = {},
    focusRequester: FocusRequester = FocusRequester(),
    leftIconId: Int = R.drawable.ic_menu,
    onClickLeftIcon: () -> Unit = {},
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier.padding(horizontal = 12.dp),
            onClick = onClickLeftIcon,
            iconId = leftIconId,
        )
        SearchField(
            value = searchValue,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            enabled = inputEnabled,
            onClick = onClickInput,
            focusRequester = focusRequester
        )
        IconButton(
            onClick = { },
            iconId = null,
        )
    }
}