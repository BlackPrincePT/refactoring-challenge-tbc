package com.example.challenge.data.network.model.mappers

import com.example.challenge.data.network.model.ConnectionDto
import com.example.challenge.domain.model.GetConnection

fun ConnectionDto.toDomain(): GetConnection {
    return GetConnection(
        id = id,
        avatar = avatar.orEmpty(),
        email = email,
        fullName = "$firstName $lastName"
    )
}