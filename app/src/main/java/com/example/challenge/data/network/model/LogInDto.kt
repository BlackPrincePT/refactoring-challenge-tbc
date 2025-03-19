package com.example.challenge.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LogInDto(
    @Json(name = "needsMfa") var needsMfa: Boolean? = null,
    @Json(name = "AccessToken") var accessToken: String? = null,
    @Json(name = "RefreshToken") var refreshToken: String? = null
)