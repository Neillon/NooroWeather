package com.neillon.weather.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.neillon.weather.R

@Composable
internal fun EmptyWeatherDataContent(modifier: Modifier = Modifier) {
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
