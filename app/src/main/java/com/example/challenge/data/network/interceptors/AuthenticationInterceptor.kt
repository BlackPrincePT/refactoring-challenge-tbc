package com.example.challenge.data.network.interceptors

import com.example.challenge.domain.cache.CacheManager
import com.example.challenge.domain.cache.PreferenceKeys
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(private val dataStoreManager: CacheManager) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val authToken = runBlocking { dataStoreManager.observe(key = PreferenceKeys.TOKEN).first() }
        val newRequest = if (!authToken.isNullOrBlank()) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $authToken")
                .build()
        } else {
            chain.request()
        }

        return chain.proceed(newRequest)
    }
}