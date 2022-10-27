package com.alaishat.ahmed.jetstore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaishat.ahmed.jetstore.R
import com.alaishat.ahmed.jetstore.ui.components.buttons.SecondaryButton
import com.alaishat.ahmed.jetstore.ui.theme.JetStoreTheme

/**
 * Created by Ahmed Al-Aishat on Aug/04/2022.
 * JetStore Project.
 */
@Composable
fun EmptyItems(
    imageId: Int,
    titleText: String,
    subtitle: String? = null,
    buttonText: String? = null,
    onClickButton: (() -> Unit)? = null,
) {
    Column(
        Modifier.padding(horizontal = 35.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = imageId), contentDescription = null)
        Text(
            text = titleText,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        if (subtitle != null) {
            VerticalSpacer(height = 20.dp)
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        if (onClickButton != null && buttonText != null) {
            VerticalSpacer(height = 30.dp)
            SecondaryButton(text = buttonText, onClick = onClickButton)
        }
    }
}

@Composable
@Preview
fun EmptyItemsPreview() {
    JetStoreTheme {
        EmptyItems(
            imageId = R.drawable.sally_no_favorites,
            titleText = "No favorites yet",
            subtitle = "Hit the orange button down\n" +
                    "below to Create an order",
            buttonText = "Start ordering",
            onClickButton = { }
        )
    }
}