package com.example.network.dto
import kotlinx.serialization.Serializable

@Serializable
data class OfferDto(
    val button: ButtonDto? = null,
    val id: String? = null,
    val link: String,
    val title: String
)