package com.alaishat.ahmed.mobostore.data.payment

import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.model.PaymentMethod

/**
 * Created by Ahmed Al-Aishat on Oct/04/2022.
 * Mobo Store Project.
 */
val visaCard = PaymentMethod(
    "**** **** **** 1234",
    R.drawable.visa,
)
val masterCard = PaymentMethod(
    "**** **** **** 2345",
    R.drawable.master,
)
val bankCard = PaymentMethod(
    "**** **** **** 3456",
    R.drawable.bank,
)

val paymentCards = listOf(
    visaCard, masterCard, bankCard
)