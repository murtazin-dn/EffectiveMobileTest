package com.example.favorites.data.repository

import com.example.common.result.Result
import com.example.favorites.data.model.VacancyModel
import kotlinx.coroutines.flow.Flow

internal interface VacanciesRepository {
    suspend fun getVacancies(): Flow<Result<List<VacancyModel>>>
    suspend fun setFavorite(id: String)
    suspend fun unsetFavorite(id: String)
}