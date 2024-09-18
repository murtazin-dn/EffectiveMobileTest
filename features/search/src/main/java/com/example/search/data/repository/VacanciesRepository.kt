package com.example.search.data.repository

import com.example.common.result.Result
import com.example.search.data.model.OffersVacanciesModel
import kotlinx.coroutines.flow.Flow

internal interface VacanciesRepository {
    suspend fun getVacancies(): Flow<Result<OffersVacanciesModel>>
    suspend fun setFavorite(id: String)
    suspend fun unsetFavorite(id: String)
}