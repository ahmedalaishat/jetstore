package com.alaishat.ahmed.jetstore.ui.components.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alaishat.ahmed.jetstore.ui.theme.Dimension
import com.alaishat.ahmed.jetstore.ui.theme.JetStoreTheme

/**
 * Created by Ahmed Al-Aishat on Aug/21/2022.
 * JetStore Project.
 */
@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AppButton(
        text = text,
        onClick = onClick,
        modifier = modifier
            .width(Dimension.SecondaryButtonWidth)
            .height(Dimension.SecondaryButtonHeight),
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary,
        textStyle = MaterialTheme.typography.labelMedium,
    )
}

@Composable
@Preview
fun SecondaryButtonPreview() {
    JetStoreTheme {
        SecondaryButton(text = "Secondary Button", onClick = { })
    }
}