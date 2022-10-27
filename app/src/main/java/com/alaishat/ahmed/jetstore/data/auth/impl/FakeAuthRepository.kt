package com.alaishat.ahmed.jetstore.data.auth.impl

import com.alaishat.ahmed.jetstore.data.Result
import com.alaishat.ahmed.jetstore.data.auth.AuthRepository
import com.alaishat.ahmed.jetstore.data.auth.appUsers
import com.alaishat.ahmed.jetstore.model.User
import com.skydoves.whatif.whatIfNotNullWith
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Ahmed Al-Aishat on Oct/05/2022.
 * JetStore Project.
 */
class FakeAuthRepository @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
) : AuthRepository {
    private val user = MutableStateFlow<User?>(null)

    override suspend fun login(email: String, password: String): Result<Boolean> {
        return withContext(ioDispatcher) {
            delay(1000) // pretend we're on a slow network
            val foundUser = appUsers.find { it.email == email && it.password == password }
            foundUser.whatIfNotNullWith(
                whatIf = {
                    user.value = foundUser
                    Result.Success(true)
                },
                whatIfNot = {
                    Result.Error(IllegalArgumentException("Unable to find product"))
                }
            )
        }
    }

    override fun logout() {
        user.value = null
    }

    override fun observeUser(): Flow<User?> {
        return user
    }

}