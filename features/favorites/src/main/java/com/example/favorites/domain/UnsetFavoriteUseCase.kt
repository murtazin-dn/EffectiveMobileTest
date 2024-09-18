package com.example.favorites.domain

import com.example.favorites.data.repository.VacanciesRepository
import javax.inject.Inject

internal class UnsetFavoriteUseCase @Inject constructor(
    private val vacanciesRepository: VacanciesRepository
) {
    suspend fun execute(id: String) = vacanciesRepository.unsetFavorite(id)
}