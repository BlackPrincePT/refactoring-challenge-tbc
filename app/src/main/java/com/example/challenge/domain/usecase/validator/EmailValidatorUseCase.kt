package com.example.challenge.domain.usecase.validator

import com.example.challenge.domain.core.ValidationError
import com.example.challenge.domain.core.ValidationResult
import javax.inject.Inject

class EmailValidatorUseCase @Inject constructor() {

    private val emailRegex = Regex(
        "[a-zA-Z0-9+._%-]{1,256}" +
                "@" +
                "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                "(\\.[a-zA-Z0-9][a-zA-Z0-9-]{0,25})+"
    )

    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult.Failure(ValidationError.Email.BLANK)
        }
        if (!email.matches(emailRegex)) {
            return ValidationResult.Failure(ValidationError.Email.INVALID_FORMAT)
        }
        return ValidationResult.Success
    }
}