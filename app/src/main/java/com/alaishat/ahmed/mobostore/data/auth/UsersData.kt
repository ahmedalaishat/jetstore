package com.alaishat.ahmed.mobostore.data.auth

import com.alaishat.ahmed.mobostore.model.Address
import com.alaishat.ahmed.mobostore.model.User

/**
 * Created by Ahmed Al-Aishat on Oct/05/2022.
 * Mobo Store Project.
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