package com.neillon.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neillon.common.SimpleStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val simpleStorage: SimpleStorage
) : ViewModel() {

    val uiState: StateFlow<String?> = simpleStorage
        .getValueAsStream("city")
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun saveCity(cityName: String) = viewModelScope.launch {
        simpleStorage.storeValue("city", cityName)
    }
}