package com.neillon.nooroweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.neillon.nooroweather.ui.theme.NooroWeatherTheme
import com.neillon.weather.presentation.WeatherScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NooroWeatherTheme {
                WeatherScreen()
            }
        }
    }
}
