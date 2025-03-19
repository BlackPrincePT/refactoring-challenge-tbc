package com.example.challenge.presentation.screen.connection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge.VSS.presentation.mapper.connection.toPresentation
import com.example.challenge.domain.core.Resource
import com.example.challenge.domain.model.GetConnection
import com.example.challenge.domain.usecase.connection.GetConnectionsUseCase
import com.example.challenge.domain.usecase.cache.ClearCacheUseCase
import com.example.challenge.presentation.core.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ConnectionsViewModel @Inject constructor(
    private val getConnections: GetConnectionsUseCase,
    private val clearCache: ClearCacheUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConnectionsUiState())
    val uiState: StateFlow<ConnectionsUiState> = _uiState.asStateFlow()

    fun onEvent(event: ConnectionsUiEvent) {
        when (event) {
            ConnectionsUiEvent.LogOut -> logOut()
        }
    }

    init {
        fetchConnections()
    }

    private fun fetchConnections() = viewModelScope.launch {
        getConnections()
            .collect { resource ->
                _uiState.update {
                    when (resource) {
                        is Resource.Success -> it.copy(connections = resource.data.map(GetConnection::toPresentation), isLoading = false)
                        is Resource.Loading -> it.copy(isLoading = true)
                        is Resource.Failure -> it.copy(errorMessage = Event(resource.error), isLoading = false)
                    }
                }
            }
    }

    private fun logOut() = viewModelScope.launch {
        clearCache()
        _uiState.update { it.copy(shouldNavigateToLogin = true) }
    }
}
