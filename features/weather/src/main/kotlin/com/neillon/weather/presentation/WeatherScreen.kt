package com.neillon.weather.presentation

import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.neillon.weather.presentation.components.EmptyContent
import com.neillon.weather.presentation.components.SelectableCityItem
import com.neillon.weather.presentation.components.UnsetCityContent
import com.neillon.weather.presentation.components.WeatherContent

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    WeatherScreenContent(
        uiState = state.value,
        onSearch = { query ->
            if (query.isNotEmpty()) {
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
            WeatherUiState.Empty -> UnsetCityContent(modifier = Modifier.padding(innerPadding))
            is WeatherUiState.Idle -> WeatherContent(
                modifier = Modifier.padding(innerPadding),
                uiState.weatherData
            )

            is WeatherUiState.Searching -> WeatherSearchingContent(
                modifier = Modifier.padding(innerPadding),
                uiState = uiState,
                onCitySelected = onCitySelected
            )
        }
    }
}


@Composable
fun WeatherSearchingContent(
    modifier: Modifier = Modifier, uiState: WeatherUiState.Searching,
    onCitySelected: (String) -> Unit
) {
    uiState.data?.let {
        Column {
            Spacer(modifier = Modifier.height(32.dp))
            SelectableCityItem(modifier, onCitySelected, uiState)
        }
    } ?: EmptyContent()
}
