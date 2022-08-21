package com.alaishat.ahmed.mobostore.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable

/**
 * Created by Ahmed Al-Aishat on Jul/31/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
inline fun <reified Activity : ComponentActivity> Context.getActivity(): Activity? {
    return when (this) {
        is Activity -> this
        else -> {
            var context = this
            while (context is ContextWrapper) {
                context = context.baseContext
                if (context is Activity) return context
            }
            null
        }
    }
}

val WindowInsets.Companion.barsPadding: PaddingValues
    @Composable
    @NonRestartableComposable
    get() = PaddingValues(
        top = statusBars.asPaddingValues().calculateTopPadding(),
        bottom = navigationBars.asPaddingValues().calculateBottomPadding()
    )
