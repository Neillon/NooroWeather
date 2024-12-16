package com.neillon.common.android

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.neillon.common.SimpleStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.util.concurrent.ConcurrentHashMap

/**
 * Default implementation of [SimpleStorage] using Android's [DataStore] API
 */
class DefaultSimpleStorage(
    storageName: String,
    val context: Context
) : SimpleStorage {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = storageName)

    /**
     * Only one [Preferences.Key] should be created per key string
     * This map ensures that [String] values are mapped to [Preferences.Key] strings
     * No duplication allowed
     */
    private val stringKeys = ConcurrentHashMap<String, Preferences.Key<String>>()
    private fun keyOf(key: String) = stringKeys.getOrPut(key) { stringPreferencesKey(key) }


    override suspend fun storeValue(key: String, value: String) {
        context.dataStore.edit { preferences -> preferences[keyOf(key)] = value }
    }

    override fun getValueAsStream(key: String): Flow<String?> =
        context.dataStore.data.map { preferences -> preferences[keyOf(key)] }

    override suspend fun getValue(key: String): String? =
        context.dataStore.data.map { preferences -> preferences[keyOf(key)] }.firstOrNull()
}