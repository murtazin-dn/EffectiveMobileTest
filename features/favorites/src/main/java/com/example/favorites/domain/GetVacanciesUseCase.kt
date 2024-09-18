package com.example.favorites.domain

import com.example.favorites.data.repository.VacanciesRepository
import javax.inject.Inject

internal class GetVacanciesUseCase @Inject constructor(
    private val repository: VacanciesRepository
){
    suspend fun execute() = repository.getVacancies()
}