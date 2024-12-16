package com.neillon.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend inline fun <T> doApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline call: suspend () -> T
): Result<T> {
    return withContext(dispatcher) {
        try {
            call()?.let { Result.success(it) } ?: Result.failure(Exception("No data"))
        } catch (e: Exception) {
            Result.failure(Exception(e.message))
        }
    }
}