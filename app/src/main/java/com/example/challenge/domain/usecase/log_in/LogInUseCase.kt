package com.example.challenge.domain.usecase.log_in

import com.example.challenge.domain.repository.LogInRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val logInRepository: LogInRepository) {
    operator fun invoke(email: String, password: String) = logInRepository.logIn(email = email, password = password)
}