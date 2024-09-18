package com.example.effectivemobiletest.data.repository

import kotlinx.coroutines.flow.Flow

internal interface VacanciesRepository {
    fun getFavoritesCount(): Flow<Int>
}