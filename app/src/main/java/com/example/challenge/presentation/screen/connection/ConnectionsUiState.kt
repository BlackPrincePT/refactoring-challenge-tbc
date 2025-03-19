package com.example.challenge.presentation.screen.connection

import com.example.challenge.domain.core.Error
import com.example.challenge.presentation.core.Event
import com.example.challenge.presentation.model.Connection

data class ConnectionsUiState(
    val connections: List<Connection> = emptyList(),
    val isLoading: Boolean = false,
    val shouldNavigateToLogin: Boolean = false,
    val errorMessage: Event<Error>? = null
)
