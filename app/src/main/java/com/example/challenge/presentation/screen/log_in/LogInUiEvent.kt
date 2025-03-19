package com.example.challenge.presentation.screen.log_in

sealed interface LogInUiEvent {
    data class Login(val email: String, val password: String) : LogInUiEvent
}