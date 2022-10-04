package com.alaishat.ahmed.mobostore.model

import androidx.annotation.DrawableRes

/**
 * Created by Ahmed Al-Aishat on Oct/04/2022.
 * Mobo Store Project.
 */
data class PaymentMethod(
    val carNumber: String,
    @DrawableRes val cardImage: Int,
)