package com.neillon.data.repository

import com.neillon.common.network.doApiCall
import com.neillon.data.service.WeatherApiResponse
import com.neillon.data.service.WeatherService
import com.neillon.domain.Weather
import com.neillon.domain.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    val dispatcher: CoroutineDispatcher,
    val weatherService: WeatherService
) : WeatherRepository {

    override suspend fun search(query: String): Weather {
        return doApiCall(dispatcher) { weatherService.search(query) }
            .map { it.toDomain() }
            .getOrThrow()
    }

    private fun WeatherApiResponse.toDomain() = Weather(
        city = location.name,
        temperature = current.tempC,
        humidity = current.humidity,
        uv = current.uv,
        feelsLike = current.feelslikeC,
        iconUrl = current.condition.icon
    )
}