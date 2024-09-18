package com.example.search.data.model

import com.example.network.dto.VacancyDto


internal data class VacancyModel(
    val address: com.example.search.data.model.AddressModel,
    val appliedNumber: Int? = null,
    val company: String,
    val description: String? = null,
    val experience: com.example.search.data.model.ExperienceModel,
    val id: String,
    val isFavorite: Boolean,
    val lookingNumber: Int? = null,
    val publishedDate: String,
    val questions: List<String>,
    val responsibilities: String,
    val salary: SalaryModel,
    val schedules: List<String>,
    val title: String
)

internal fun VacancyDto.toModel() = VacancyModel(
    id = this.id,
    isFavorite = isFavorite,
    lookingNumber = lookingNumber,
    title = title,
    address = com.example.search.data.model.AddressModel(
        house = this.address.house,
        street = this.address.street,
        town = this.address.town
    ),
    appliedNumber = this.appliedNumber,
    company = this.company,
    description = this.description,
    experience = com.example.search.data.model.ExperienceModel(
        text = this.experience.text,
        previewText = this.experience.previewText
    ),
    publishedDate = this.publishedDate,
    questions = this.questions,
    responsibilities = this.responsibilities,
    salary = SalaryModel(
        full = this.salary.full,
        short = this.salary.short
    ),
    schedules = this.schedules
)

