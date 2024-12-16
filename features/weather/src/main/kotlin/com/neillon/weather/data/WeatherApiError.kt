package com.neillon.weather.data

import com.google.gson.annotations.SerializedName

// 400 - BAD request errors
data class ApiError(
    @SerializedName("error") val error: ApiErrorData
)

data class ApiErrorData(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)