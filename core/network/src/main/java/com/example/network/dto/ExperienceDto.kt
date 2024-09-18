package com.example.network.dto
import kotlinx.serialization.Serializable

@Serializable
data class ExperienceDto(
    val previewText: String,
    val text: String
)