package com.example.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class VacancyDto(
    val address: AddressDto,
    val appliedNumber: Int? = null,
    val company: String,
    val description: String? = null,
    val experience: ExperienceDto,
    val id: String,
    val isFavorite: Boolean,
    val lookingNumber: Int? = null,
    val publishedDate: String,
    val questions: List<String>,
    val responsibilities: String,
    val salary: SalaryDto,
    val schedules: List<String>,
    val title: String
)