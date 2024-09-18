package com.example.effectivemobiletest.data.repository

import com.example.database.dao.VacancyDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultVacanciesRepository @Inject constructor(
    private val dao: VacancyDao
): VacanciesRepository{
    override fun getFavoritesCount(): Flow<Int> = dao.getCount()

}