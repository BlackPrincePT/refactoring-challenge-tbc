package com.example.challenge.domain.repository

import com.example.challenge.domain.core.DataError
import com.example.challenge.domain.core.Resource
import com.example.challenge.domain.model.GetToken
import kotlinx.coroutines.flow.Flow

interface LogInRepository {
    fun logIn(email: String, password: String): Flow<Resource<GetToken, DataError.Network>>
}