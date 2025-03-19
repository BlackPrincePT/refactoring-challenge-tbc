package com.example.challenge.domain.usecase.connection

import com.example.challenge.domain.repository.ConnectionsRepository
import javax.inject.Inject

class GetConnectionsUseCase @Inject constructor(private val connectionsRepository: ConnectionsRepository) {
    operator fun invoke() = connectionsRepository.getConnections()
}