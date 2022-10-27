package com.alaishat.ahmed.jetstore.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alaishat.ahmed.jetstore.ui.theme.Dimension
import com.alaishat.ahmed.jetstore.utils.silentClickable

/**
 * Created by Ahmed Al-Aishat on Aug/21/2022.
 * JetStore Project.
 */
@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconId: Int?,
    iconTint: Color = Color.Unspecified,
    iconSize: Dp = Dimension.smIcon,
    backgroundColor: Color = Color.Transparent,
    shape: Shape = CircleShape,
    elevation: Dp = Dimension.zero,
    paddingValue: PaddingValues = PaddingValues(iconSize.value.div(2).dp),
    silentClick: Boolean = false
) {
    if (iconId == null)
        Spacer(
            modifier = modifier
                .padding(paddingValue)
                .size(iconSize)
        )
    else
        Box(
            modifier = modifier
                .shadow(elevation = elevation, shape = shape)
                .clip(shape)
                .background(backgroundColor)
                .silentClickable(silent = silentClick) { onClick() }
                .padding(paddingValues = paddingValue)
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Modifier
                    .size(iconSize)
                    .align(Alignment.Center),
                tint = iconTint,
            )
        }
}