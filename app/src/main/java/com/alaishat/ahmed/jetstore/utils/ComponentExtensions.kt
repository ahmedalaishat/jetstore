package com.alaishat.ahmed.jetstore.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp

/**
 * Created by Ahmed Al-Aishat on Jul/31/2022.
 * JetStore Project.
 */
fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(key = "header", span = { GridItemSpan(this.maxLineSpan) }, content = content)
}

fun LazyGridScope.footer(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(key = "footer", span = { GridItemSpan(this.maxLineSpan) }, content = content)
}

fun Modifier.advancedShadow(
    color: Color = Color.Black,
    alpha: Float = 0f,
    cornersRadius: Dp = 0.dp,
    shadowBlurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = drawBehind {

    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparentColor = color.copy(alpha = 0f).toArgb()

    drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowBlurRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            cornersRadius.toPx(),
            cornersRadius.toPx(),
            paint
        )
    }
}

fun Modifier.animatePage(
    pageOffset: Float,
    startScale: Dp = 0.8.dp,
    stopScale: Dp = 1.dp,
    startAlpha: Dp = 0.8.dp,
    stopAlpha: Dp = 1.dp,
) = graphicsLayer {

    // We animate the scaleX + scaleY, between 80% and 100%
    lerp(
        start = startScale,
        stop = stopScale,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    ).also { scale ->
        scaleX = scale.value
        scaleY = scale.value
    }

    // We animate the alpha, between 75% and 100%
    alpha = lerp(
        start = startAlpha,
        stop = stopAlpha,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    ).value
}

fun Modifier.silentClickable(
    silent: Boolean = true,
    onClick: () -> Unit
) =
    if (!silent) clickable { onClick() }
    else clickable(
        interactionSource = MutableInteractionSource(),
        indication = null,
        onClick = onClick
    )