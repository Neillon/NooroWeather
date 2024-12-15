package com.neillon.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    WeatherScreenContent(state.value, onSaveCity = { })
}

@Composable
private fun WeatherScreenContent(uiState: String?, onSaveCity: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("City Name is: $uiState")
        Button(onClick = { onSaveCity("New York") }) {
            Text("Click to Save City")
        }
    }
}

@Preview
@Composable
private fun WeatherScreenPreview() {
    WeatherScreenContent("New York", onSaveCity = {})
}