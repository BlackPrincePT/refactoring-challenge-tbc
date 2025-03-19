package com.example.challenge.data.repository

import com.example.challenge.data.network.core.NetworkUtils
import com.example.challenge.data.network.model.LogInDto
import com.example.challenge.domain.core.Resource
import com.example.challenge.data.network.model.mappers.toDomain
import com.example.challenge.data.network.service.LogInService
import com.example.challenge.domain.core.DataError
import com.example.challenge.domain.core.convert
import com.example.challenge.domain.model.GetToken
import com.example.challenge.domain.repository.LogInRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogInRepositoryImpl @Inject constructor(
    private val logInService: LogInService,
    private val networkUtils: NetworkUtils,
) : LogInRepository {

    override fun logIn(
        email: String,
        password: String
    ): Flow<Resource<GetToken, DataError.Network>> {

        val resultFlow = networkUtils.handleHttpRequest {
            logInService.logIn(email = email, password = password)
        }

        return resultFlow.convert(LogInDto::toDomain)
    }
}