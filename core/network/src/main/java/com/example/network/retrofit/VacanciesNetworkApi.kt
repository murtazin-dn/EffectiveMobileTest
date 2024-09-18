package com.example.network.retrofit

import com.example.network.dto.OffersVacanciesResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface VacanciesNetworkApi{
    @GET("u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getVacancies(): Response<OffersVacanciesResponse>
}
