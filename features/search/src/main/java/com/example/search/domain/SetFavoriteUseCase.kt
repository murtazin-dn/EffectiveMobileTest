package com.example.search.domain

import com.example.search.data.repository.VacanciesRepository
import javax.inject.Inject

internal class SetFavoriteUseCase @Inject constructor(
    private val vacanciesRepository: VacanciesRepository
) {
    suspend fun execute(id: String) = vacanciesRepository.setFavorite(id)
}