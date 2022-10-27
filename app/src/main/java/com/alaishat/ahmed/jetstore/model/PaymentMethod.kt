package com.alaishat.ahmed.jetstore.model

import androidx.annotation.DrawableRes

/**
 * Created by Ahmed Al-Aishat on Oct/04/2022.
 * JetStore Project.
 */
data class PaymentMethod(
    val carNumber: String,
    @DrawableRes val cardImage: Int,
)