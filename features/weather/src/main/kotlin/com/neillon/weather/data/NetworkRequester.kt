package com.neillon.weather.data

import android.annotation.SuppressLint
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

object NetworkRequester {

    @SuppressLint("NewApi")
    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(dispatcher) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> Resource.Error(throwable.localizedMessage ?: "")
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = convertErrorBody(throwable)
                        Resource.ErrorResponse(errorResponse)
                    }

                    else -> {
                        Resource.Error(message = "Generic error")
                    }
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ApiError? {
        return try {
            throwable.response()?.errorBody()?.string()
                .let { Gson().fromJson(it, ApiError::class.java) }
        } catch (exception: Exception) {
            null
        }
    }
}