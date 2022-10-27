package com.alaishat.ahmed.jetstore.ui.components.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaishat.ahmed.jetstore.ui.components.BallPulseSyncIndicator
import com.alaishat.ahmed.jetstore.ui.theme.JetStoreTheme

/**
 * Created by Ahmed Al-Aishat on Jul/31/2022.
 * JetStore Project.
 */
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
    loading: Boolean = false
) {
    Box(modifier = modifier) {
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxSize(),
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                disabledContainerColor = containerColor.copy(alpha = 0.7f)
            ),
            shape = RoundedCornerShape(12.dp),
            enabled = !loading
        ) {
            if (loading)
                BallPulseSyncIndicator(
                    modifier = Modifier.absoluteOffset(0.dp, 0.dp),
                    ballsColor = contentColor,
                    ballSize = 8.dp
                )
            else
                Text(
                    text = text,
                    color = contentColor,
                    style = textStyle
                )
        }
    }
}

@Composable
@Preview
fun AppButtonPreview() {
    JetStoreTheme {
        AppButton(text = "Hello Button", onClick = { })
    }
}