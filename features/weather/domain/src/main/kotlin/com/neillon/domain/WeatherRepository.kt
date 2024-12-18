package com.neillon.domain

interface WeatherRepository {
    suspend fun search(query: String): Weather
}