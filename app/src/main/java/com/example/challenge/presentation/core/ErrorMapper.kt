package com.example.challenge.presentation.core

import com.example.challenge.R
import com.example.challenge.domain.core.DataError
import com.example.challenge.domain.core.Error
import com.example.challenge.domain.core.ValidationError

fun Error.toStringResId(): Int = when (this) {
    is DataError -> this.toStringResId()
    is ValidationError -> this.toStringResId()
}

private fun DataError.toStringResId(): Int = when (this) {
    is DataError.Network -> when (this) {
        DataError.Network.UNKNOWN -> R.string.error_unknown
        DataError.Network.TIMEOUT -> R.string.error_timeout
        DataError.Network.NO_CONNECTION -> R.string.error_no_connection
        DataError.Network.BAD_REQUEST -> R.string.error_bad_request
        DataError.Network.UNAUTHORIZED -> R.string.error_unauthorized
        DataError.Network.FORBIDDEN -> R.string.error_forbidden
        DataError.Network.NOT_FOUND -> R.string.error_not_found
        DataError.Network.METHOD_NOT_ALLOWED -> R.string.error_method_not_allowed
        DataError.Network.CONFLICT -> R.string.error_conflict
        DataError.Network.INTERNAL_SERVER_ERROR -> R.string.error_internal_server_error
        DataError.Network.BAD_GATEWAY -> R.string.error_bad_gateway
        DataError.Network.SERVICE_UNAVAILABLE -> R.string.error_service_unavailable
        DataError.Network.GATEWAY_TIMEOUT -> R.string.error_gateway_timeout
    }
}

private fun ValidationError.toStringResId(): Int = when (this) {

    is ValidationError.Email -> when (this) {
        ValidationError.Email.BLANK -> R.string.error_email_blank
        ValidationError.Email.INVALID_FORMAT -> R.string.error_email_invalid_format
    }

    is ValidationError.Password -> when (this) {
        ValidationError.Password.BLANK -> R.string.error_password_blank
    }
}
