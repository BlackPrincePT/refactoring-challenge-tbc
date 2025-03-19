package com.example.challenge.data.network.model.mappers

import com.example.challenge.data.network.model.LogInDto
import com.example.challenge.domain.model.GetToken

fun LogInDto.toDomain(): GetToken {
    return GetToken(
        needsMfa = needsMfa ?: false,
        accessToken = accessToken ?: "",
        refreshToken = refreshToken ?: ""
    )
}
