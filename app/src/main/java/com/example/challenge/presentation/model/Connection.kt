package com.example.challenge.presentation.model

data class Connection(
    val id: Int,
    val avatar: String,
    val email: String,
    val fullName: String,
    val isSelected: Boolean = false
)