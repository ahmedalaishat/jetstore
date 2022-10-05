package com.alaishat.ahmed.mobostore.data.auth

import com.alaishat.ahmed.mobostore.data.Repository
import com.alaishat.ahmed.mobostore.data.Result
import com.alaishat.ahmed.mobostore.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ahmed Al-Aishat on Oct/05/2022.
 * Mobo Store Project.
 */
interface AuthRepository : Repository {

    /**
     * Login and store user info
     */
    suspend fun login(email: String, password: String): Result<Boolean>

    /**
     * Logout and clear user info
     */
    fun logout()

    /**
     * Observe the logged user
     */
    fun observeUser(): Flow<User?>
//
//    /**
//     * Check if there a logged user or not
//     */
//    fun isLoggedIn(): Boolean = getUser() != null

}