package com.example.search.domain

import com.example.search.data.repository.VacanciesRepository
import javax.inject.Inject

internal class UnsetFavoriteUseCase @Inject constructor(
    private val vacanciesRepository: VacanciesRepository
) {
    suspend fun execute(id: String) = vacanciesRepository.unsetFavorite(id)
}