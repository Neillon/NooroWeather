package com.neillon.common

import kotlinx.coroutines.flow.Flow

/**
 * Simple key/value storage to store values that survive app restart
 */
interface SimpleStorage {

    /**
     * Stores a value based on a key/value pair
     *
     * @param key The key to be stored
     * @param value The value of the [key]
     */
    suspend fun storeValue(key: String, value: String)

    /**
     * Get the value based on a key
     *
     * @param key The key to retrieve a value
     * @return A nullable [Flow] with the value retrieved
     */
    fun getValueAsStream(key: String): Flow<String?>
}