package com.neillon.weather.data

sealed class Resource<T>(
    val data: T?,
    val message: String?,
    val errorResponse: ApiError?
) {
    class Success<T>(data: T) : Resource<T>(data, null, null)
    class Error<T>(message: String) : Resource<T>(null, message, null)
    class ErrorResponse<T>(errorResponse: ApiError?) : Resource<T>(null, null, errorResponse)
}