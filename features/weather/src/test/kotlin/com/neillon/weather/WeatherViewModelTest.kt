package com.neillon.weather

import com.neillon.common.SimpleStorage
import com.neillon.domain.Weather
import com.neillon.domain.WeatherRepository
import com.neillon.weather.presentation.WeatherSearchState
import com.neillon.weather.presentation.WeatherUiState
import com.neillon.weather.presentation.WeatherViewModel
import com.neillon.weather.presentation.toPresentationModel
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

const val CITY_KEY = "city"

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {
    private val fakeSimpleStorage = FakeSimpleStorage()
    private val weatherRepository: WeatherRepository = mockk()
    private lateinit var viewModel: WeatherViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        viewModel = WeatherViewModel(fakeSimpleStorage, weatherRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `init should observe CITY_KEY from SimpleStorage and update uiState`() = runTest {
        // Arrange
        // Ensure that WeatherRepository is initialized before any call on init block
        coEvery { weatherRepository.search(any()) } returns weatherResponse
        viewModel = WeatherViewModel(fakeSimpleStorage, weatherRepository)

        // Act
        advanceUntilIdle()

        // Assert
        assertEquals(
            WeatherUiState(weatherData = weatherResponse.toPresentationModel()),
            viewModel.uiState.value
        )
    }

    @Test
    fun `onSearch should update uiState with search result`() = runTest {
        // Arrange
        coEvery { weatherRepository.search(any()) } returns weatherResponse
        fakeSimpleStorage.storeValue(CITY_KEY, "Paris")

        // Act
        viewModel.onSearch("Paris")
        advanceUntilIdle()

        // Assert
        val uiState = viewModel.uiState.value
        assertEquals(
            WeatherUiState(
                searchState = WeatherSearchState(
                    query = "Paris",
                    weatherData = weatherResponse.toPresentationModel()
                )
            ),
            uiState
        )
    }

    @Test
    fun `onCitySelected should update SimpleStorage and clear search`() = runTest {
        // Arrange
        val cityName = "New York"
        fakeSimpleStorage.storeValue(CITY_KEY, "Berlin")

        // Act
        viewModel.onCitySelected(cityName)
        advanceUntilIdle()

        // Assert
        // Verify that the city has been updated in FakeSimpleStorage
        val storedCity = fakeSimpleStorage.getValueAsStream(CITY_KEY).first()
        assertEquals(cityName, storedCity)

        // Check that the search state is cleared
        assertEquals(WeatherSearchState.empty(), viewModel.uiState.value.searchState)
    }

    @Test
    fun `onSearchUpdate should update the search query in uiState`() = runTest {
        // Arrange
        fakeSimpleStorage.storeValue(CITY_KEY, weatherResponse.city)
        coEvery { weatherRepository.search(any()) } returns weatherResponse
        // Call the onSearch to initialize uiState
        viewModel.onSearch(weatherResponse.city)
        advanceUntilIdle()

        // Act
        viewModel.onSearchUpdate(weatherResponse.city, "Amsterdam")
        advanceUntilIdle()

        // Assert
        val uiState = viewModel.uiState.value
        assertEquals("Amsterdam", uiState.searchState.query)
    }

    companion object {
        val weatherResponse = Weather(
            city = "City",
            temperature = 20.0,
            humidity = 65,
            uv = 7.0,
            feelsLike = 18.0,
            iconUrl = "https://cdn.testicon.com/weather/128x128/rainy.png"
        )
    }
}