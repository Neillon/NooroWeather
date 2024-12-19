package com.neillon.weather

import com.neillon.common.SimpleStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSimpleStorage : SimpleStorage {
    private val storage = mutableMapOf<String, String?>()

    override suspend fun storeValue(key: String, value: String) {
        storage[key] = value
    }


    override fun getValueAsStream(key: String): Flow<String?> =
        flow { emit(storage[key]) }

    override suspend fun getValue(key: String): String? = storage[key]
}