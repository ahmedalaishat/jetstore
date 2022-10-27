package com.alaishat.ahmed.jetstore.data.payment

import com.alaishat.ahmed.jetstore.R
import com.alaishat.ahmed.jetstore.model.PaymentMethod

/**
 * Created by Ahmed Al-Aishat on Oct/04/2022.
 * JetStore Project.
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