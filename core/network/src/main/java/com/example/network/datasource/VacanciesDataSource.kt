package com.example.network.datasource

import com.example.common.network.ApiResponse
import com.example.network.dto.OffersVacanciesResponse

interface VacanciesDataSource {
    suspend fun getVacancies(): ApiResponse<OffersVacanciesResponse>
}