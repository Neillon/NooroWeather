package com.neillon.weather.presentation

const val EMPTY_STRING = ""

fun Any?.isNull() = this == null
fun Any?.isNotNull() = !isNull()

data class WeatherSearchState(
    val query: String,
    val weatherData: WeatherData? = null
) {
    val isSearching: Boolean
        get() = query.isNotEmpty()

    val isEmptyWeatherData: Boolean
        get() = weatherData.isNull()

    companion object {
        // Factory method to create an empty search state
        fun empty() = WeatherSearchState(EMPTY_STRING)
    }
}

data class WeatherData(
    val city: String,
    val temperature: Int,
    val humidity: Int,
    val uv: Int,
    val feelsLike: Int,
    val iconUrl: String
)

data class WeatherUiState(
    val searchState: WeatherSearchState = WeatherSearchState.empty(),
    val weatherData: WeatherData? = null
) {
    val isEmptyWeatherData: Boolean
        get() = weatherData.isNull()
    val hasWeatherData: Boolean
        get() = weatherData.isNotNull()

    companion object {
        fun empty() = WeatherUiState()
    }
}
