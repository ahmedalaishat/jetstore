package com.alaishat.ahmed.jetstore.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaishat.ahmed.jetstore.R
import com.alaishat.ahmed.jetstore.data.Result
import com.alaishat.ahmed.jetstore.data.auth.AuthRepository
import com.alaishat.ahmed.jetstore.data.auth.userAhmed
import com.alaishat.ahmed.jetstore.model.User
import com.alaishat.ahmed.jetstore.utils.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ahmed Al-Aishat on Oct/05/2022.
 * JetStore Project.
 */

data class LoginUiState(
    val user: User? = null,
    val loginEmail: String = "",
    val loginPassword: String = "",
    val loginEmailError: Int? = null,
    val loginPasswordError: Int? = null,
    val isLoading: Boolean = false,
) {
    fun isLoggedIn(): Boolean {
        return user != null
    }
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    // See https://proandroiddev.com/android-singleliveevent-redux-with-kotlin-flow-b755c70bb055
    // For why channel > SharedFlow/StateFlow in this case
    private val _oneShotEvents = Channel<OneShotEvent>(Channel.BUFFERED)
    val oneShotEvents = _oneShotEvents.receiveAsFlow()

    init {

        // Observe for user changes in the repo layer
        viewModelScope.launch {
            authRepository.observeUser().collect { user ->
                _uiState.update { it.copy(user = user, isLoading = false) }
            }
        }
    }

    fun login() {
        if (isValidInput())
            viewModelScope.launch {
                _uiState.update { it.copy(isLoading = true) }
                val result = authRepository.login(userAhmed.email, userAhmed.password)
                if (result is Result.Error)
                    _oneShotEvents.send(OneShotEvent.ShowError(R.string.user_not_found))
            }
    }

    private fun isValidInput(): Boolean {
        when {
            _uiState.value.loginEmail.isBlank() -> {
                _uiState.update { it.copy(loginEmailError = R.string.email_required) }
            }
            !_uiState.value.loginEmail.isValidEmail() -> {
                _uiState.update { it.copy(loginEmailError = R.string.invalid_email) }
            }
            _uiState.value.loginPassword.isBlank() -> {
                _uiState.update { it.copy(loginPasswordError = R.string.password_required) }
            }
            else -> return true
        }
        return false
    }

    fun logout() {
        authRepository.logout()
    }

    fun onUpdateEmail(email: String) {
        _uiState.update {
            it.copy(loginEmail = email, loginEmailError = null)
        }
    }

    fun onUpdatePassword(password: String) {
        _uiState.update {
            it.copy(loginPassword = password, loginPasswordError = null)
        }
    }

    sealed class OneShotEvent(val message: Int? = null) {
        open class ShowError(message: Int?) : OneShotEvent(message)
        object NetworkError : ShowError(R.string.network_error)
    }
}