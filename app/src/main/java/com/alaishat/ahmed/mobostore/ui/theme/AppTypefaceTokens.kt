package com.alaishat.ahmed.mobostore.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.alaishat.ahmed.mobostore.R

/**
 * Created by Ahmed Al-Aishat on Jul/31/2022.
 * Mobo Store Project.
 */

val RelewayFont = FontFamily(
    Font(R.font.raleway_regular, FontWeight.Normal),
    Font(R.font.raleway_bold, FontWeight.Bold)
)

internal object AppTypefaceTokens {
    val Brand = RelewayFont
    val Plain = RelewayFont
    val WeightExtraBold = FontWeight.ExtraBold
    val WeightBold = FontWeight.Bold
    val WeightSemiBold = FontWeight.SemiBold
    val WeightMedium = FontWeight.Medium
    val WeightRegular = FontWeight.Normal
}
