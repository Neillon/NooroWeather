package com.neillon.weather.presentation

import com.neillon.domain.Weather

internal fun Weather.toPresentationModel() = WeatherData(
    city = city,
    temperature = temperature.toInt(),
    humidity = humidity,
    uv = uv.toInt(),
    feelsLike = feelsLike.toInt(),
    iconUrl = "https:" + iconUrl.replace("64x64", "128x128")
)