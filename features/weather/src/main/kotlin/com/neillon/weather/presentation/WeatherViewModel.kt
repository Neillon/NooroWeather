package com.neillon.weather.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neillon.domain.WeatherRepository
import com.neillon.common.SimpleStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val CITY = "city"

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val simpleStorage: SimpleStorage,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState.empty())
    val uiState: StateFlow<WeatherUiState>
        get() = _uiState.asStateFlow()
    private var isInitialized = false

    init {
        if (!isInitialized) {
            viewModelScope.launch {
                simpleStorage.getValueAsStream(CITY)
                    .distinctUntilChanged()
                    .flatMapLatest { fetchCityWeather(it.orEmpty()) }
                    .collect { _uiState.value = it }
            }
        }

        isInitialized = true
    }

    private fun fetchCityWeather(city: String): Flow<WeatherUiState> = flow {
        runCatching {
            val weatherInformation = weatherRepository.search(city)
            emit(WeatherUiState(weatherData = weatherInformation.toPresentationModel()))
        }.getOrElse {
            emit(WeatherUiState.empty())
        }
    }

    // Public
    fun onSearch(cityName: String) = viewModelScope.launch {
        try {
            val weatherInformation = weatherRepository.search(cityName)

            val searchState = _uiState.value.searchState
            _uiState.value = _uiState.value.copy(
                searchState = searchState.copy(
                    query = cityName,
                    weatherData = weatherInformation.toPresentationModel()
                )
            )
        } catch (e: Exception) {
            Log.i(TAG, "onSearch: Error trying to search for city -> ${e.message}")
        }
    }

    fun onCitySelected(name: String) = viewModelScope.launch {
        simpleStorage.storeValue(CITY, name)
        onSearchCleared()
    }

    fun onSearchUpdate(prev: String, curr: String) {
        val searchState = _uiState.value.searchState
        _uiState.value = _uiState.value.copy(
            searchState = searchState.copy(
                query = curr
            )
        )
        if (prev.isNotEmpty() && curr.isEmpty()) {
            onSearchCleared()
        }
    }

    private fun onSearchCleared() = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(searchState = WeatherSearchState.empty())
    }

}
