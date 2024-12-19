package com.neillon.data

import com.neillon.data.repository.toDomain
import com.neillon.data.service.Condition
import com.neillon.data.service.Current
import com.neillon.data.service.Location
import com.neillon.data.service.WeatherApiResponse
import org.junit.Assert
import org.junit.Test

class WeatherMapperTest {

    companion object {
        private const val CITY = "Mumbai"
        private const val ICON_URL = "https://cdn.iconUrl.com"
        private const val TEMP_C = 9.5
        private const val HUMIDITY = 50
        private const val UV = 10.9
        private const val FEELS_LIKE = 6.6
    }

    @Test
    fun shouldMapApiResponseToDomainModel() {
        // GIVEN
        val dataModel = WeatherApiResponse(
            location = Location(
                name = CITY,
                region = "Asia",
                country = "India",
                lat = 10.0,
                lon = 20.0,
                tzId = "tzId",
                localtimeEpoch = 1029,
                localtime = "10:29"
            ),
            current = Current(
                tempC = TEMP_C,
                humidity = HUMIDITY,
                uv = UV,
                feelslikeC = FEELS_LIKE,
                lastUpdatedEpoch = 0,
                lastUpdated = "",
                tempF = 0.0,
                isDay = 0,
                condition = Condition(
                    code = 10,
                    text = "Cloudy",
                    icon = ICON_URL
                ),
                windMph = 0.0,
                windKph = 0.0,
                windDegree = 0,
                windDir = "",
                pressureMb = 0,
                pressureIn = 0.0,
                precipMm = 0.0,
                precipIn = 0.0,
                cloud = 0,
                feelslikeF = 0.0,
                windchillC = 0.0,
                windchillF = 0.0,
                heatindexC = 0.0,
                heatindexF = 0.0,
                dewpointC = 0.0,
                dewpointF = 0.0,
                visKm = 0.0,
                visMiles = 0.0,
                gustMph = 0.0,
                gustKph = 0.0
            )

        )
        // WHEN
        val domainModel = dataModel.toDomain()

        // THEN
        Assert.assertTrue(domainModel.city == CITY)
        Assert.assertTrue(domainModel.iconUrl == ICON_URL)
        Assert.assertTrue(domainModel.temperature == TEMP_C)
        Assert.assertTrue(domainModel.humidity == HUMIDITY)
        Assert.assertTrue(domainModel.uv == UV)
        Assert.assertTrue(domainModel.feelsLike == FEELS_LIKE)
    }
}