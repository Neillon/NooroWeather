package com.neillon.nooroweather.di

import android.content.Context
import com.neillon.common.SimpleStorage
import com.neillon.common.android.DefaultSimpleStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface CommonModule {
    companion object {
        private const val DATASTORE_FILENAME = "nooro_weather"

        @Provides
        @Singleton
        fun provideSimpleStorage(@ApplicationContext appContext: Context): SimpleStorage =
            DefaultSimpleStorage(DATASTORE_FILENAME, appContext)
    }
}