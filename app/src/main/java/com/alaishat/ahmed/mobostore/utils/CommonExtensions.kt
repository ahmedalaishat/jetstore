package com.alaishat.ahmed.mobostore.utils

import android.util.Patterns


/**
 * Created by Ahmed Al-Aishat on Oct/05/2022.
 * Mobo Store Project.
 */


/**
 * Check if an email address is valid or not.
 */
fun CharSequence?.isValidEmail() =
    !isNullOrBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()