package com.neillon.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neillon.weather.presentation.WeatherUiState
import com.neillon.weather.presentation.ui.CitySearchBar

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    WeatherScreenContent(state.value)
}

@Composable
private fun WeatherScreenContent(
    uiState: WeatherUiState,
) {
    Scaffold(
        topBar = {
            CitySearchBar()
        }
    ) { innerPadding ->
        when (uiState) {
            WeatherUiState.Empty -> NoSelectedCityContent(modifier = Modifier.padding(innerPadding))
            is WeatherUiState.Idle -> Box(modifier = Modifier.fillMaxSize())
            is WeatherUiState.Searching -> Box(modifier = Modifier.fillMaxSize())
        }
    }
}


@Composable
fun NoSelectedCityContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.no_Selected_city),
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            stringResource(R.string.search_recommendation),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Preview
@Composable
private fun WeatherScreenPreview() {
    WeatherScreenContent(WeatherUiState.Empty)
}