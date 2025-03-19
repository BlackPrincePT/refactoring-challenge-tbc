package com.example.challenge.domain.usecase.validator

import com.example.challenge.domain.core.ValidationError
import com.example.challenge.domain.core.ValidationResult
import javax.inject.Inject

class PasswordValidatorUseCase @Inject constructor() {
    operator fun invoke(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult.Failure(ValidationError.Password.BLANK)
        }

        return ValidationResult.Success
    }
}