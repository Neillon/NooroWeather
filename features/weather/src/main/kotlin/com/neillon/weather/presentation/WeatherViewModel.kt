package com.neillon.weather.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neillon.common.SimpleStorage
import com.neillon.weather.domain.Weather
import com.neillon.weather.domain.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val CITY = "city"

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val simpleStorage: SimpleStorage,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Empty)
    val uiState: StateFlow<WeatherUiState>
        get() = _uiState.asStateFlow()
    private var isInitialized = false

    init {
        viewModelScope.launch {
            simpleStorage.getValueAsStream(CITY)
                .distinctUntilChanged()
                .flatMapLatest { performSearch(it.orEmpty()) }
                .collect { _uiState.value = it }
        }

        isInitialized = true
    }

    private fun performSearch(city: String): Flow<WeatherUiState> = flow {
        runCatching {
            val weather = weatherRepository.search(city)
            emit(WeatherUiState.Idle(weather.toPresentationModel()))
        }.getOrElse {
            emit(WeatherUiState.Empty)
        }
    }

    private fun Weather.toPresentationModel() = WeatherData(
        city = city,
        temperature = temperature.toInt(),
        humidity = humidity,
        uv = uv.toInt(),
        feelsLike = feelsLike.toInt(),
        iconUrl = "https:" + iconUrl.replace("64x64", "128x128")
    )

    // Public
    fun onSearch(cityName: String) = viewModelScope.launch {
        _uiState.value = WeatherUiState.Searching(cityName)
        try {
            val weatherData = weatherRepository.search(cityName)
            _uiState.value = WeatherUiState.Searching(cityName, weatherData.toPresentationModel())
        } catch (e: Exception) {
            Log.i("Neilon", "onSearch: Error trying to serach -> ${e.message}")
            _uiState.value = WeatherUiState.Searching(cityName)
        }
    }

    fun onSearchCleared() = viewModelScope.launch {
        simpleStorage.getValue(CITY)?.let(::onCitySelected) ?: run {
            _uiState.value = WeatherUiState.Empty
        }
    }

    fun onCitySelected(name: String) = viewModelScope.launch {
        simpleStorage.storeValue(CITY, name)
    }
}