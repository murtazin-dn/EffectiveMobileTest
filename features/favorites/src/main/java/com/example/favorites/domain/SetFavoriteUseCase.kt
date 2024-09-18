package com.example.favorites.domain

import com.example.favorites.data.repository.VacanciesRepository
import javax.inject.Inject

internal class SetFavoriteUseCase @Inject constructor(
    private val vacanciesRepository: VacanciesRepository
) {
    suspend fun execute(id: String) = vacanciesRepository.setFavorite(id)
}