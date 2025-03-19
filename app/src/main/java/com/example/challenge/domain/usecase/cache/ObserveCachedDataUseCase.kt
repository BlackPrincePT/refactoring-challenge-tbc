package com.example.challenge.domain.usecase.cache

import androidx.datastore.preferences.core.Preferences
import com.example.challenge.domain.cache.CacheManager
import javax.inject.Inject

class ObserveCachedDataUseCase @Inject constructor(private val cacheManager: CacheManager) {
    operator fun <T> invoke(key: Preferences.Key<T>) = cacheManager.observe(key = key)
}