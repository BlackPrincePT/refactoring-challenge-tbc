package com.example.challenge.domain.usecase.cache

import com.example.challenge.domain.cache.CacheManager
import javax.inject.Inject

class ClearCacheUseCase @Inject constructor(private val cacheManager: CacheManager) {
    suspend operator fun invoke() = cacheManager.clear()
}