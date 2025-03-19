package com.example.challenge.domain.repository

import com.example.challenge.domain.core.DataError
import com.example.challenge.domain.core.Resource
import com.example.challenge.domain.model.GetConnection
import kotlinx.coroutines.flow.Flow

interface ConnectionsRepository {
    fun getConnections(): Flow<Resource<List<GetConnection>, DataError.Network>>
}