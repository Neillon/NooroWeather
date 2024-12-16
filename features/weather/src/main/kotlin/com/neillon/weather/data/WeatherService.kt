package com.neillon.weather.data

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("current.json")
    suspend fun search(@Query("q") query: String): ApiResponse
}