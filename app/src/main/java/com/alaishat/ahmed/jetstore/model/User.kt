package com.alaishat.ahmed.jetstore.model

/**
 * Created by Ahmed Al-Aishat on Oct/05/2022.
 * JetStore Project.
 */
data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    // TODO: Encrypt me
    val password: String,
    val address: Address,
) {
    fun fullName() = "$firstName $lastName"
}
