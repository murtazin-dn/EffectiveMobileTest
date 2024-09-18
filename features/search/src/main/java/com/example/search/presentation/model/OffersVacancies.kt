package com.example.search.presentation.model

import com.example.search.data.model.OffersVacanciesModel

internal data class OffersVacancies(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>
)
internal fun OffersVacanciesModel.toUi() = OffersVacancies(
    offers = this.offers.map { it.toUi() },
    vacancies = this.vacancies.map { it.toUi() }
)