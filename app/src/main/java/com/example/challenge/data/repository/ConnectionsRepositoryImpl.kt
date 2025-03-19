package com.example.challenge.data.repository

import com.example.challenge.data.network.core.NetworkUtils
import com.example.challenge.data.network.model.ConnectionDto
import com.example.challenge.data.network.model.mappers.toDomain
import com.example.challenge.data.network.service.ConnectionsService
import com.example.challenge.domain.core.DataError
import com.example.challenge.domain.core.Resource
import com.example.challenge.domain.core.convert
import com.example.challenge.domain.model.GetConnection
import com.example.challenge.domain.repository.ConnectionsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConnectionsRepositoryImpl @Inject constructor(
    private val connectionsService: ConnectionsService,
    private val networkUtils: NetworkUtils,
) : ConnectionsRepository {

    override fun getConnections(): Flow<Resource<List<GetConnection>, DataError.Network>> {

        val resultFlow = networkUtils.handleHttpRequest { connectionsService.getConnections() }

        return resultFlow.convert { list ->
            list.map(ConnectionDto::toDomain)
        }
    }
}