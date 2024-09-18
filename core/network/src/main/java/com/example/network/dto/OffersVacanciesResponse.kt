package com.example.network.dto
import kotlinx.serialization.Serializable

@Serializable
data class OffersVacanciesResponse(
    val offers: List<OfferDto>,
    val vacancies: List<VacancyDto>
)