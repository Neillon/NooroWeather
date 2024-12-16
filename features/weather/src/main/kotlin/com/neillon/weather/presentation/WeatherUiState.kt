package com.neillon.weather.presentation

data class WeatherData(
    val city: String,
    val temperature: Int,
    val humidity: Int,
    val uv: Int,
    val feelsLike: Int,
    val iconUrl: String
)

sealed class WeatherUiState {
    data object Empty: WeatherUiState()
    data class Idle(val weatherData: WeatherData): WeatherUiState()
    data class Searching(val query: String, val data: WeatherData? = null): WeatherUiState()
}