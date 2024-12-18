package com.neillon.nooroweather.di

import com.neillon.nooroweather.BuildConfig
import com.neillon.data.repository.WeatherRepositoryImpl
import com.neillon.data.service.WeatherService
import com.neillon.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class WeatherModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("key", BuildConfig.API_KEY)
                    .build()

                val request = original.newBuilder().url(url).build()
                return@addInterceptor chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    fun provideWeatherRepository(service: WeatherService): WeatherRepository =
        WeatherRepositoryImpl(Dispatchers.IO, service)
}
