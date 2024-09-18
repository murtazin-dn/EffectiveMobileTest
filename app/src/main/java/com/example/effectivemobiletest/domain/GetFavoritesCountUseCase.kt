package com.example.effectivemobiletest.domain

import com.example.effectivemobiletest.data.repository.VacanciesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetFavoritesCountUseCase @Inject constructor(
    private val vacanciesRepository: VacanciesRepository
) {
    fun execute(): Flow<Int> = vacanciesRepository.getFavoritesCount()
}