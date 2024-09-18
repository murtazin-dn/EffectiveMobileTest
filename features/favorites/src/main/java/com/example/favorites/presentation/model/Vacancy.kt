package com.example.favorites.presentation.model

import com.example.favorites.data.model.AddressModel
import com.example.favorites.data.model.ExperienceModel
import com.example.favorites.data.model.SalaryModel
import com.example.favorites.data.model.VacancyModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

internal data class Vacancy(
    val address: Address,
    val appliedNumber: Int? = null,
    val company: String,
    val description: String? = null,
    val experience: Experience,
    val id: String,
    val isFavorite: Boolean,
    val lookingNumber: Int? = null,
    val publishedDate: String,
    val questions: List<String>,
    val responsibilities: String,
    val salary: Salary,
    val schedules: List<String>,
    val title: String
) : FavoritesScreenItem

internal fun VacancyModel.toUi() = Vacancy(
    id = this.id,
    isFavorite = isFavorite,
    lookingNumber = lookingNumber,
    title = title,
    address = Address(
        house = this.address.house,
        street = this.address.street,
        town = this.address.town
    ),
    appliedNumber = this.appliedNumber,
    company = this.company,
    description = this.description,
    experience = Experience(
        text = this.experience.text,
        previewText = this.experience.previewText
    ),
    publishedDate = formatDate(this.publishedDate),
    questions = this.questions,
    responsibilities = this.responsibilities,
    salary = Salary(
        full = this.salary.full,
        short = this.salary.short
    ),
    schedules = this.schedules
)

private fun formatDate(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale("ru"))
    val outputFormat = SimpleDateFormat("d MMMM", Locale("ru"))

    val date = inputFormat.parse(inputDate)
    return outputFormat.format(date!!)
}
