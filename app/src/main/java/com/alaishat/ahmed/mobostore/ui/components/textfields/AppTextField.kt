package com.alaishat.ahmed.mobostore.ui.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.*
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alaishat.ahmed.mobostore.ui.theme.OnSurfaceVariant

/**
 * Created by Ahmed Al-Aishat on Jul/31/2022.
 * Mobo Store Project.
 */

val MinWidth = 280.dp
val MinHeight = 32.dp
val TextFieldVerPadding = 10.dp
val TextFieldHorPadding = 4.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    label: String,
    labelIconId: Int,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    errorMsgId: String = "",
    isError: Boolean = errorMsgId.isNotEmpty(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults.filledShape,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
) {
    // If color is not provided via the text style, use content color as a default
    val textColor = textStyle.color.takeOrElse {
        colors.textColor(enabled).value
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    Column(modifier = modifier) {
        Label(label = label, labelIconId = labelIconId)
        CompositionLocalProvider(LocalTextSelectionColors provides colors.selectionColors) {
            BasicTextField(
                value = value,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.containerColor(enabled).value, shape)
                    .indicatorLine(enabled, isError, interactionSource, colors)
                    .defaultMinSize(minWidth = MinWidth, minHeight = MinHeight),
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
                        leadingIcon = leadingIcon,
                        trailingIcon = trailingIcon,
                        singleLine = singleLine,
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        contentPadding = appTextFieldPadding()
                    )
                }
            )
        }
        if (isError) {
            Text(
                text = errorMsgId,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
private fun Label(label: String, labelIconId: Int) {
    Row {
        Icon(painter = painterResource(id = labelIconId), contentDescription = label, tint = OnSurfaceVariant)
        Text(
            text = label,
            color = OnSurfaceVariant,
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@ExperimentalMaterial3Api
fun appTextFieldPadding(
    start: Dp = TextFieldHorPadding,
    top: Dp = TextFieldVerPadding,
    end: Dp = TextFieldHorPadding,
    bottom: Dp = TextFieldVerPadding
): PaddingValues = PaddingValues(start, top, end, bottom)