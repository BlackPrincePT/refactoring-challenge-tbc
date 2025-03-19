package com.example.challenge.domain.core


sealed interface ValidationResult {
    data object Success : ValidationResult
    data class Failure(val error: ValidationError) : ValidationResult
}

sealed interface ValidationError : Error {
    enum class Email : ValidationError {
        BLANK,
        INVALID_FORMAT
    }

    enum class Password : ValidationError {
        BLANK
    }
}