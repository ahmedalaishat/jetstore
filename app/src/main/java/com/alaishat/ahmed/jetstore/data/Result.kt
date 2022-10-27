package com.alaishat.ahmed.jetstore.data

/**
 * Created by Ahmed Al-Aishat on Sep/28/2022.
 * JetStore Project.
 */

/**
 * A generic class that holds a value or an exception
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}