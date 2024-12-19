package com.neillon.weather

import com.neillon.domain.Weather
import com.neillon.weather.presentation.toPresentationModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class PresentationModelTest {

    @Test
    fun `should map Weather to WeatherData correctly`() {
        // Arrange
        val weather = Weather(
            city = "Test City",
            temperature = 23.7,
            humidity = 55,
            uv = 7.8,
            feelsLike = 22.5,
            iconUrl = "//cdn.testicon.com/weather/64x64/day/sunny.png"
        )

        // Act
        val result = weather.toPresentationModel()

        // Assert
        assertEquals("Test City", result.city)
        assertEquals(23, result.temperature) // Rounded down
        assertEquals(55, result.humidity)
        assertEquals(7, result.uv) // Rounded down
        assertEquals(22, result.feelsLike) // Rounded down
        assertEquals("https://cdn.testicon.com/weather/128x128/day/sunny.png", result.iconUrl)
    }

    @Test
    fun `should handle iconUrl with different formats`() {
        // Arrange
        val weather = Weather(
            city = "Another City",
            temperature = 30.0,
            humidity = 65,
            uv = 10.5,
            feelsLike = 32.0,
            iconUrl = "//cdn.othericon.com/icons/64x64/cloudy.png"
        )

        // Act
        val result = weather.toPresentationModel()

        // Assert
        assertEquals("https://cdn.othericon.com/icons/128x128/cloudy.png", result.iconUrl)
    }

    @Test
    fun `should handle integer temperature and uv values correctly`() {
        // Arrange
        val weather = Weather(
            city = "Integer City",
            temperature = 25.7,
            humidity = 60,
            uv = 5.2,
            feelsLike = 26.0,
            iconUrl = "//cdn.icon.com/weather/64x64/rainy.png"
        )

        // Act
        val result = weather.toPresentationModel()

        // Assert
        assertEquals(25, result.temperature)
        assertEquals(5, result.uv)
    }
}