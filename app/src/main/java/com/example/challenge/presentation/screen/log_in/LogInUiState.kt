package com.example.challenge.presentation.screen.log_in

import com.example.challenge.domain.core.Error
import com.example.challenge.presentation.core.Event

data class LogInUiState(
    val isLoading: Boolean = false,
    val shouldNavigateToConnections: Boolean = false,
    val errorMessage: Event<Error>? = null,
)