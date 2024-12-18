package com.neillon.weather.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neillon.weather.presentation.WeatherData

@Composable
internal fun WeatherSearchContent(
    modifier: Modifier = Modifier,
    weatherData: WeatherData,
    onCitySelected: (String) -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(32.dp))
        SelectableCityItem(modifier, onCitySelected, weatherData)
    }
}