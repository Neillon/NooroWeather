package com.neillon.weather.data.service

import com.google.gson.annotations.SerializedName

// 400 - BAD request errors
data class WeatherApiError(
    @SerializedName("error") val error: WeatherApiErrorData
)

data class WeatherApiErrorData(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)