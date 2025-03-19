package com.example.challenge.data.network.core

import com.example.challenge.domain.core.DataError
import com.example.challenge.domain.core.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class NetworkUtils @Inject constructor() {
    fun <T> handleHttpRequest(apiCall: suspend () -> Response<T>): Flow<Resource<T, DataError.Network>> {
        return flow {
            emit(Resource.Loading)

            try {
                val response = apiCall.invoke()

                if (response.isSuccessful) {
                    response.body()?.let { emit(Resource.Success(it)) }
                        ?: emit(Resource.Failure(DataError.Network.UNKNOWN))
                } else {
                    val error = mapResponseCodeToError(response.code())
                    emit(Resource.Failure(error))
                }
            } catch (e: Exception) {
                println(e)
                emit(Resource.Failure(mapExceptionToNetworkError(e)))
            }

        }.flowOn(Dispatchers.IO)
    }

    private fun mapResponseCodeToError(code: Int): DataError.Network {
        return when (code) {
            400 -> DataError.Network.BAD_REQUEST
            401 -> DataError.Network.UNAUTHORIZED
            403 -> DataError.Network.FORBIDDEN
            404 -> DataError.Network.NOT_FOUND
            405 -> DataError.Network.METHOD_NOT_ALLOWED
            408 -> DataError.Network.TIMEOUT
            409 -> DataError.Network.CONFLICT
            422 -> DataError.Network.BAD_REQUEST
            500 -> DataError.Network.INTERNAL_SERVER_ERROR
            502 -> DataError.Network.BAD_GATEWAY
            503 -> DataError.Network.SERVICE_UNAVAILABLE
            504 -> DataError.Network.GATEWAY_TIMEOUT
            else -> DataError.Network.UNKNOWN
        }
    }

    private fun mapExceptionToNetworkError(e: Exception): DataError.Network {
        return when (e) {
            is HttpException -> mapResponseCodeToError(e.code())
            is SocketTimeoutException -> DataError.Network.TIMEOUT
            is UnknownHostException -> DataError.Network.NO_CONNECTION
            is ConnectException -> DataError.Network.NO_CONNECTION
            is IOException -> DataError.Network.NO_CONNECTION
            else -> DataError.Network.UNKNOWN
        }
    }
}
