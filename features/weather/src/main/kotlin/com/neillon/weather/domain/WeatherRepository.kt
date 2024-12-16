package com.neillon.weather.domain

interface WeatherRepository {
    suspend fun search(query: String): Result<Weather>
}