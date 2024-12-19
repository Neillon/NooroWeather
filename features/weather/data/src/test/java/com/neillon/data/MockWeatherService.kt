package com.neillon.data

import com.neillon.data.service.Condition
import com.neillon.data.service.Current
import com.neillon.data.service.Location
import com.neillon.data.service.WeatherApiResponse
import com.neillon.data.service.WeatherService

class MockWeatherService(private val shouldFailRequest: Boolean) : WeatherService {

    override suspend fun search(query: String): WeatherApiResponse {
        if (shouldFailRequest) throw Exception("Failed to get data")

        return WeatherApiResponse(
            location = Location(
                name = "Default City",
                region = "Default Region",
                country = "Default Country",
                lat = 0.0,
                lon = 0.0,
                tzId = "Default/Timezone",
                localtimeEpoch = 0,
                localtime = "1970-01-01 00:00"
            ),
            current = Current(
                lastUpdatedEpoch = 0,
                lastUpdated = "",
                tempC = 9.5,
                tempF = 0.0,
                isDay = 0,
                condition = Condition(
                    text = "Cloudy",
                    icon = "https://cdn.iconUrl.com",
                    code = 10
                ),
                windMph = 0.0,
                windKph = 0.0,
                windDegree = 0,
                windDir = "",
                pressureMb = 0,
                pressureIn = 0.0,
                precipMm = 0.0,
                precipIn = 0.0,
                humidity = 50,
                cloud = 0,
                feelslikeC = 6.6,
                feelslikeF = 0.0,
                windchillC = 0.0,
                windchillF = 0.0,
                heatindexC = 0.0,
                heatindexF = 0.0,
                dewpointC = 0.0,
                dewpointF = 0.0,
                visKm = 0.0,
                visMiles = 0.0,
                uv = 10.9,
                gustMph = 0.0,
                gustKph = 0.0
            )
        )
    }
}