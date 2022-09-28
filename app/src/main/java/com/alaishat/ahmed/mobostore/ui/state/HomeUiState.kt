package com.alaishat.ahmed.mobostore.ui.state

/**
 * Created by Ahmed Al-Aishat on Sep/22/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
data class HomeUiState(
    val isSignedIn: Boolean = false,
    val isPremium: Boolean = false,
    val newsItems: List<HomeItemUiState> = listOf(),
    val userMessages: List<Message> = listOf()
)