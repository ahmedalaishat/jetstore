package com.alaishat.ahmed.jetstore.ui.state

/**
 * Created by Ahmed Al-Aishat on Sep/22/2022.
 * JetStore Project.
 */
data class HomeUiState(
    val isSignedIn: Boolean = false,
    val isPremium: Boolean = false,
    val newsItems: List<HomeItemUiState> = listOf(),
    val userMessages: List<Message> = listOf()
)