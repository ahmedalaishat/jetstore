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
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    reverseColors: Boolean = false
) {
    val primary = MaterialTheme.colorScheme.primary
    val onPrimary = MaterialTheme.colorScheme.onPrimary
    AppButton(
        text = text,
        onClick = onClick,
        modifier = modifier
            .width(Dimension.PrimaryButtonWidth)
            .height(Dimension.PrimaryButtonHeight),
        containerColor = if (reverseColors) onPrimary else primary,
        contentColor = if (reverseColors) primary else onPrimary,
        loading = loading
    )
}

@Composable
@Preview
fun PrimaryButtonPreview() {
    JetStoreTheme {
        PrimaryButton(text = "Primary Button", onClick = { })
    }
}