package com.example.search.data.model
import com.example.network.dto.OffersVacanciesResponse

internal data class OffersVacanciesModel(
    val offers: List<com.example.search.data.model.OfferModel>,
    val vacancies: List<VacancyModel>
)

internal fun OffersVacanciesResponse.toModel() = OffersVacanciesModel(
    offers = this.offers.map { offer ->
        offer.toModel()
    },
    vacancies = this.vacancies.map { vacancy ->
        vacancy.toModel()
    }
)