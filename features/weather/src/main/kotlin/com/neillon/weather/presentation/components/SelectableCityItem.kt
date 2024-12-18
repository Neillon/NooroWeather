package com.neillon.weather.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.neillon.weather.R
import com.neillon.weather.presentation.WeatherData

@Composable
fun SelectableCityItem(
    modifier: Modifier = Modifier,
    onCitySelected: (String) -> Unit,
    weatherData: WeatherData,
) {
    Surface(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(16.dp)),
        onClick = { onCitySelected(weatherData.city) }
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
                Text(weatherData.city, style = MaterialTheme.typography.headlineSmall)
                Row(verticalAlignment = Alignment.Top) {
                    Text(weatherData.temperature.toString(), style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.width(2.dp))
                    Image(
                        modifier = Modifier.padding(top = 10.dp).size(6.dp),
                        painter = painterResource(R.drawable.temperature_celsius),
                        contentDescription = null
                    )
                }
            }
            AsyncImage(
                modifier = Modifier.width(84.dp),
                contentScale = ContentScale.Fit,
                model = weatherData.iconUrl,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun SelectableCityItem() {
//    SelectableCityItem(
//        onCitySelected = {}
//    )
}