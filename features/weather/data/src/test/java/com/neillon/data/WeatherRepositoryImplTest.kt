package com.neillon.data

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class WeatherRepositoryImplTest {
    @Test
    fun `search should return WeatherApiResponse when request succeeds`() = runTest {
        // Arrange
        val mockService = MockWeatherService(shouldFailRequest = false)

        // Act
        val result = mockService.search("query")

        // Assert
        assertEquals("Default City", result.location.name)
        assertEquals("Default Region", result.location.region)
        assertEquals("Default Country", result.location.country)
        assertEquals(9.5, result.current.tempC, 0.0)
        assertEquals("Cloudy", result.current.condition.text)
    }

    @Test
    fun `search should throw exception when request fails`() = runTest {
        // Arrange
        val mockService = MockWeatherService(shouldFailRequest = true)

        // Act & Assert
        val exception = try {
            mockService.search("query")
            null // If no exception, this line will execute (unexpected)
        } catch (e: Exception) {
            e // Capture the exception
        }

        assertEquals("Failed to get data", exception?.message)
    }

}