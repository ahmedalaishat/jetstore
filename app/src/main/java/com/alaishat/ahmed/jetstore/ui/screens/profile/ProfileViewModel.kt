package com.alaishat.ahmed.jetstore.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaishat.ahmed.jetstore.data.auth.AuthRepository
import com.alaishat.ahmed.jetstore.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ahmed Al-Aishat on Oct/05/2022.
 * JetStore Project.
 */

data class ProfileUiState(
    val user: User? = null,
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        // Observe for user changes in the repo layer
        viewModelScope.launch {
            authRepository.observeUser().collect { user ->
                _uiState.update { it.copy(user = user) }
            }
        }
    }
}