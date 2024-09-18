package com.example.search.data.repository

import com.example.common.network.ApiResponse
import com.example.common.result.Result
import com.example.database.dao.VacancyDao
import com.example.database.entity.VacancyDbEntity
import com.example.network.datasource.VacanciesDataSource
import com.example.search.data.model.OffersVacanciesModel
import com.example.search.data.model.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultVacanciesRepository @Inject constructor(
    private val dataSource: VacanciesDataSource,
    private val dao: VacancyDao
): VacanciesRepository {
    override suspend fun getVacancies(): Flow<Result<OffersVacanciesModel>> = withContext(Dispatchers.IO){
        flow {
            when (val data = dataSource.getVacancies()) {
                is ApiResponse.Success -> {
                    val result = data.value
                    dao.getVacancyEntities(result.vacancies.map { it.id }.toSet()).collect{ favoritesEntities ->
                        val favorites = favoritesEntities.map { it.id }
                        emit(
                            Result.success(
                                if (favorites.isEmpty()) result.toModel()
                                else result.copy(vacancies = result.vacancies.map { vacancy ->
                                    vacancy.copy(isFavorite = favorites.contains(vacancy.id))
                                }).toModel()
                            )
                        )
                    }

                }
                is ApiResponse.Error -> {
                    emit(Result.getResultFromError(data))
                }
            }
        }
    }

    override suspend fun setFavorite(id: String) = withContext(Dispatchers.IO) {
        dao.insertVacancy(VacancyDbEntity(id = id))
    }

    override suspend fun unsetFavorite(id: String) = withContext(Dispatchers.IO) {
        dao.deleteVacancy(id)
    }
}