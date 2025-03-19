package com.example.challenge.domain.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed interface Resource<out D, out E : Error> {
    data class Success<out D>(val data: D) : Resource<D, Nothing>
    data class Failure<out E : Error>(val error: E) : Resource<Nothing, E>
    data object Loading : Resource<Nothing, Nothing>
}

fun <T, D, E : Error> Flow<Resource<D, E>>.convert(transform: (D) -> T): Flow<Resource<T, E>> {
    return map { resource ->
        resource.map(transform)
    }
}

private fun <T, D, E : Error> Resource<D, E>.map(transform: (D) -> T): Resource<T, E> {
    return when (this) {
        is Resource.Success -> Resource.Success(transform(data))
        is Resource.Failure -> Resource.Failure(error)
        is Resource.Loading -> Resource.Loading
    }
}