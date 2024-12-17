package com.neillon.weather.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.neillon.weather.R
import com.neillon.weather.presentation.WeatherData

@Composable
fun WeatherContent(modifier: Modifier = Modifier, weatherData: WeatherData) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Mid
        Spacer(modifier = Modifier.height(32.dp))
        AsyncImage(
            modifier = Modifier.size(180.dp),
            contentScale = ContentScale.Fit,
            clipToBounds = true,
            model = weatherData.iconUrl,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(weatherData.city, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.width(6.dp))
            Image(painter = painterResource(R.drawable.location_icon), contentDescription = null)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.Top) {
            Text(weatherData.temperature.toString(), style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.width(2.dp))
            Image(
                modifier = Modifier.padding(top = 14.dp).size(8.dp),
                painter = painterResource(R.drawable.temperature_celsius),
                contentDescription = null
            )
        }

        // Bottom
        Spacer(modifier = Modifier.height(32.dp))
        WeatherInformation(weatherData)
    }
}

@Composable
fun WeatherInformation(weatherData: WeatherData) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 54.dp)
            .clip(RoundedCornerShape(16.dp))
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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    stringResource(R.string.humidity),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    stringResource(R.string.humidity_content, weatherData.humidity),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    stringResource(R.string.uv),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    stringResource(R.string.uv_content, weatherData.humidity),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    stringResource(R.string.feels_like),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    stringResource(R.string.temperature_content, weatherData.humidity),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun WeatherInformation() {
    WeatherContent(
        weatherData = WeatherData(
            city = "Mumbai",
            temperature = 20,
            humidity = 40,
            uv = 10,
            feelsLike = 26,
            iconUrl = ""
        )
    )
}