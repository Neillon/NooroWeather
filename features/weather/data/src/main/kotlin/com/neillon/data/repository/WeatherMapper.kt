package com.neillon.data.repository

import com.neillon.data.service.WeatherApiResponse
import com.neillon.domain.Weather

fun WeatherApiResponse.toDomain() = Weather(
    city = location.name,
    temperature = current.tempC,
    humidity = current.humidity,
    uv = current.uv,
    feelsLike = current.feelslikeC,
    iconUrl = current.condition.icon
)