package com.example.challenge.data.network.service

import com.example.challenge.BuildConfig
import com.example.challenge.data.network.model.LogInDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LogInService {
    @GET(BuildConfig.LOGIN_ENDPOINT)
    suspend fun logIn(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<LogInDto>
}