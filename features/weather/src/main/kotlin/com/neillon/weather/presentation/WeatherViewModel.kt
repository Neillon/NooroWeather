package com.neillon.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neillon.common.SimpleStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val simpleStorage: SimpleStorage
) : ViewModel() {

    val uiState: StateFlow<WeatherUiState> = simpleStorage
        .getValueAsStream("city")
        .map { WeatherUiState.Empty }
        .stateIn(viewModelScope, SharingStarted.Lazily, WeatherUiState.Empty)
}