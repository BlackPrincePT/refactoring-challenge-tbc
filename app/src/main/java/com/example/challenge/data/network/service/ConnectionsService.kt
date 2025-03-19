package com.example.challenge.data.network.service

import com.example.challenge.BuildConfig
import com.example.challenge.data.network.model.ConnectionDto
import retrofit2.Response
import retrofit2.http.GET

interface ConnectionsService {
    @GET(BuildConfig.CONNECTION_ENDPOINT)
    suspend fun getConnections(): Response<List<ConnectionDto>>
}