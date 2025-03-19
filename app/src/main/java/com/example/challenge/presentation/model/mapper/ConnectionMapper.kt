package com.example.challenge.VSS.presentation.mapper.connection

import com.example.challenge.domain.model.GetConnection
import com.example.challenge.presentation.model.Connection

fun GetConnection.toPresentation(): Connection {
    return Connection(
        id = id,
        avatar = avatar,
        email = email,
        fullName = fullName
    )
}