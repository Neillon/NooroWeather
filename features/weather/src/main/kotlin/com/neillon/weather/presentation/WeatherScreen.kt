package com.neillon.weather.presentation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neillon.weather.presentation.components.CityWeatherSearchBar
import com.neillon.weather.presentation.components.EmptyContent
import com.neillon.weather.presentation.components.EmptyWeatherDataContent
import com.neillon.weather.presentation.components.WeatherContent
import com.neillon.weather.presentation.components.WeatherSearchContent

const val TAG = "Neillon"

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    WeatherScreenContent(
        uiState = state.value,
        onSearch = viewModel::onSearch,
        onCitySelected = viewModel::onCitySelected,
        onSearchUpdate = { prev, curr ->
            // Refresh UI state since search was cleared -> Present current city data
            viewModel.onSearchUpdate(prev, curr)
        }
    )
}

@Composable
private fun WeatherScreenContent(
    uiState: WeatherUiState,
    onSearch: (String) -> Unit,
    onSearchUpdate: (prev: String, curr: String) -> Unit,
    onCitySelected: (String) -> Unit
) {
    Scaffold(
        topBar = {
            CityWeatherSearchBar(
                searchState = uiState.searchState,
                onSearch = onSearch,
                onSearchUpdate = onSearchUpdate
            )
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Log.i(TAG, "WeatherScreenContent: Searching? ${uiState.searchState.isSearching}")
        when {
            uiState.searchState.isSearching -> if (uiState.searchState.isEmptyWeatherData) {
                EmptyContent()
            } else {
                uiState.searchState.weatherData?.let { data ->
                    Log.i(TAG, "WeatherScreenContent: Has data -> $data")
                    WeatherSearchContent(
                        modifier = modifier,
                        weatherData = data,
                        onCitySelected = onCitySelected
                    )
                }
            }

            uiState.isEmptyWeatherData ->
                EmptyWeatherDataContent(modifier = modifier)

            uiState.hasWeatherData -> uiState.weatherData?.let { data ->
                WeatherContent(modifier = modifier, weatherData = data)
            }
        }
    }
}

