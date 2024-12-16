package com.neillon.weather.data

import com.neillon.weather.domain.Weather
import com.neillon.weather.domain.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    val dispatcher: CoroutineDispatcher,
    val weatherService: WeatherService
) : WeatherRepository {

    override suspend fun search(query: String): Result<Weather> {
        val response = NetworkRequester.safeApiCall(dispatcher) { weatherService.search(query) }
        return when (response) {
            is Resource.Error -> Result.failure(Exception(response.message))
            is Resource.ErrorResponse -> Result.failure(response.errorResponse.asException())
            is Resource.Success -> {
                response.data?.let { Result.success(it.toDomain()) }
                    ?: Result.failure(Exception("No data"))
            }
        }
    }

    private fun ApiError?.asException() = Exception(this?.error?.message)

    private fun ApiResponse.toDomain() = Weather(
        city = location.name,
        temperature = current.tempC,
        humidity = current.humidity,
        uv = current.uv,
        feelsLike = current.feelslikeC,
    )
}