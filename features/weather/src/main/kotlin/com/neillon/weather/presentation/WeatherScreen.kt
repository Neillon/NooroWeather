package com.neillon.weather.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neillon.weather.R
import com.neillon.weather.presentation.components.CitySearchBar

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    WeatherScreenContent(
        uiState = state.value,
        onSearch = { query ->
            if (query.isEmpty() && state.value is WeatherUiState.Searching) {
                viewModel.onSearchCleared()
            } else {
                viewModel.onSearch(query)
            }
        },
        onCitySelected = viewModel::onCitySelected
    )
}

@Composable
private fun WeatherScreenContent(
    uiState: WeatherUiState,
    onSearch: (String) -> Unit,
    onCitySelected: (String) -> Unit
) {
    Scaffold(
        topBar = {
            CitySearchBar(onSearch = onSearch)
        }
    ) { innerPadding ->
        when (uiState) {
            WeatherUiState.Empty -> NoSelectedCityContent(modifier = Modifier.padding(innerPadding))
            is WeatherUiState.Idle ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Green)
                )

            is WeatherUiState.Searching -> WeatherSearchingStateContent(
                modifier = Modifier.padding(innerPadding),
                uiState = uiState,
                onCitySelected = onCitySelected
            )
        }
    }
}

@Composable
fun WeatherSearchingStateContent(
    modifier: Modifier = Modifier, uiState: WeatherUiState.Searching,
    onCitySelected: (String) -> Unit
) {
    if (uiState.data == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
    } else {
        Column {
            Spacer(modifier = Modifier.height(32.dp))
            SelectableCityItem(modifier, onCitySelected, uiState)
        }
    }
}

@Composable
private fun SelectableCityItem(
    modifier: Modifier = Modifier,
    onCitySelected: (String) -> Unit,
    uiState: WeatherUiState.Searching,
) {
    require(uiState.data != null)
    Surface(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(16.dp)),
        onClick = { onCitySelected(uiState.query) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .clip(RoundedCornerShape(16.dp))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(uiState.data.city, style = MaterialTheme.typography.headlineSmall)
                Text(
                    stringResource(R.string.temperature, uiState.data.temperature ?: 0),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Red)
            )
        }
    }
}

@Preview
@Composable
fun SelectableCityItem() {
    SelectableCityItem(
        uiState = WeatherUiState.Searching(
            query = "Mumbai",
            data = WeatherData(
                city = "Mumbai",
                temperature = 45,
                humidity = 10,
                uv = 20,
                feelsLike = 38,
            )
        ),
        onCitySelected = {}
    )
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
