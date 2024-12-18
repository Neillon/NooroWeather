package com.neillon.domain

data class Weather(
    val city: String,
    val temperature: Double,
    val humidity: Int,
    val uv: Double,
    val feelsLike: Double,
    val iconUrl: String,
)