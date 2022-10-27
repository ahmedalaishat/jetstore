package com.alaishat.ahmed.jetstore.data.auth

import com.alaishat.ahmed.jetstore.model.Address
import com.alaishat.ahmed.jetstore.model.User

/**
 * Created by Ahmed Al-Aishat on Oct/05/2022.
 * JetStore Project.
 */

val addressAhmed = Address(
    country = "Syria",
    city = "Damascus",
    road = "Ibn Asaker Road",
    building = "M13 4GR",
)

val userAhmed = User(
    firstName = "Ahmed",
    lastName = "Al-Aishat",
    email = "ahmedalaishat@gmail.com",
    password = "A@123456",
    address = addressAhmed
)

val appUsers = listOf(userAhmed)