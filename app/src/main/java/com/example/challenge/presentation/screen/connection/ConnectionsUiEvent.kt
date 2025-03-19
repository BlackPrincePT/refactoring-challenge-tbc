package com.example.challenge.presentation.screen.connection

sealed interface ConnectionsUiEvent {
    data object LogOut : ConnectionsUiEvent
}