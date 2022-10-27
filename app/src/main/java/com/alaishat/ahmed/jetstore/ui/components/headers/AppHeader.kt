package com.alaishat.ahmed.jetstore.ui.components.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alaishat.ahmed.jetstore.R
import com.alaishat.ahmed.jetstore.ui.components.buttons.IconButton

/**
 * Created by Ahmed Al-Aishat on Aug/04/2022.
 * JetStore Project.
 */

@Composable
fun AppHeader(
    title: String,
    leftIconId: Int = R.drawable.ic_arrow_left,
    rightIconId: Int? = null,
    onClickLeftIcon: () -> Unit,
    onClickRightIcon: (() -> Unit)? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 12.dp)
    ) {
        IconButton(
            onClick = onClickLeftIcon,
            iconId = leftIconId,
        )
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .weight(1f),
            textAlign = TextAlign.Center
        )
        IconButton(
            onClick = onClickRightIcon ?: {},
            iconId = rightIconId,
        )
    }
}