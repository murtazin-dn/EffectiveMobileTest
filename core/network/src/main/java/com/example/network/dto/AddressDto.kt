package com.example.network.dto

import kotlinx.serialization.Serializable


@Serializable
data class AddressDto(
    val house: String,
    val street: String,
    val town: String
)