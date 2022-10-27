package com.alaishat.ahmed.jetstore.utils

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext

/**
 * Created by Ahmed Al-Aishat on Oct/05/2022.
 * JetStore Project.
 */


/**
 * Load a string resource.
 *
 * @param id the resource identifier
 * @return the string data associated with the resource
 */
@Composable
@ReadOnlyComposable
fun optStringResource(@StringRes id: Int?): String {
    val resources = resources()
    if (id == null) return ""
    return resources.getString(id)
}


/**
 * A composable function that returns the [Resources]. It will be recomposed when [Configuration]
 * gets updated.
 */
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}
