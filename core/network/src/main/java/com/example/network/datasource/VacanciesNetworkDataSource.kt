package com.example.network.datasource

import com.example.common.network.ApiResponse
import com.example.common.network.safeApiCall
import com.example.network.dto.OffersVacanciesResponse
import com.example.network.retrofit.VacanciesNetworkApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class VacanciesNetworkDataSource @Inject constructor(
    retrofit: Retrofit
) : VacanciesDataSource {
    private val networkApi = retrofit.create(VacanciesNetworkApi::class.java)

    override suspend fun getVacancies(): ApiResponse<OffersVacanciesResponse> =
        safeApiCall { networkApi.getVacancies() }
}