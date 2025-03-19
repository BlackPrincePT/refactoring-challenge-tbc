package com.example.challenge.presentation.screen.log_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge.domain.cache.PreferenceKeys
import com.example.challenge.domain.core.Resource
import com.example.challenge.domain.core.ValidationResult
import com.example.challenge.domain.usecase.cache.CacheDataUseCase
import com.example.challenge.domain.usecase.log_in.LogInUseCase
import com.example.challenge.domain.usecase.validator.EmailValidatorUseCase
import com.example.challenge.domain.usecase.validator.PasswordValidatorUseCase
import com.example.challenge.presentation.core.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val login: LogInUseCase,
    private val cacheData: CacheDataUseCase,
    private val validateEmail: EmailValidatorUseCase,
    private val validatePassword: PasswordValidatorUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LogInUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: LogInUiEvent) {
        when (event) {
            is LogInUiEvent.Login -> validateForm(email = event.email, password = event.password)
        }
    }

    private fun validateForm(email: String, password: String) {
        val emailValidationResult = validateEmail(email)
        val passwordValidationResult = validatePassword(password)

        if (emailValidationResult is ValidationResult.Failure) {
            _uiState.update { it.copy(errorMessage = Event(emailValidationResult.error)) }
            return
        }

        if (passwordValidationResult is ValidationResult.Failure) {
            _uiState.update { it.copy(errorMessage = Event(passwordValidationResult.error)) }
            return
        }

        signIn(email = email, password = password)
    }

    private fun signIn(email: String, password: String) = viewModelScope.launch {
        login(email = email, password = password)
            .collect { resource ->
                _uiState.update {
                    when (resource) {
                        is Resource.Loading -> it.copy(isLoading = true)
                        is Resource.Failure -> it.copy(
                            errorMessage = Event(resource.error),
                            isLoading = false
                        )

                        is Resource.Success -> {
                            cacheData(PreferenceKeys.TOKEN, resource.data.accessToken)
                            it.copy(shouldNavigateToConnections = true, isLoading = false)
                        }
                    }
                }
            }
    }
}



