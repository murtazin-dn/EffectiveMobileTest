package com.example.search.domain

import com.example.search.data.repository.VacanciesRepository
import javax.inject.Inject

internal class GetVacanciesUseCase @Inject constructor(
    private val repository: VacanciesRepository
){
    suspend fun execute() = repository.getVacancies()
}