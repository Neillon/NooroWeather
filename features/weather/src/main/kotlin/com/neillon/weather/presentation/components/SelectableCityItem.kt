package com.neillon.weather.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import coil3.compose.AsyncImage
import com.neillon.weather.R
import com.neillon.weather.presentation.WeatherData
import com.neillon.weather.presentation.WeatherUiState

@Composable
fun SelectableCityItem(
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
                    stringResource(R.string.temperature_content, uiState.data.temperature ?: 0),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
            AsyncImage(model = uiState.data.iconUrl, contentDescription = null)
        }
    }
}

@Preview
@Composable
private fun SelectableCityItem() {
    SelectableCityItem(
        uiState = WeatherUiState.Searching(
            query = "Mumbai",
            data = WeatherData(
                city = "Mumbai",
                temperature = 45,
                humidity = 10,
                uv = 20,
                feelsLike = 38,
                iconUrl = ""
            )
        ),
        onCitySelected = {}
    )
}