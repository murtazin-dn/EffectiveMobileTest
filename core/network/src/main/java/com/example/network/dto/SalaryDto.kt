package com.example.network.dto
import kotlinx.serialization.Serializable

@Serializable
data class SalaryDto(
    val full: String,
    val short: String? = null
)