package com.alaishat.ahmed.jetstore.model

/**
 * Created by Ahmed Al-Aishat on Oct/05/2022.
 * JetStore Project.
 */
data class Address(
    val country: String,
    val city: String,
    val road: String,
    val building: String,
) {
    override fun toString() = "Address: $road\n$building\n$city, $country"
}
