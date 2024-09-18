package com.example.search.di

import com.example.search.data.repository.DefaultVacanciesRepository
import com.example.search.data.repository.VacanciesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchModule {

    @Binds
    internal abstract fun bindVacanciesRepository(
        vacanciesRepository: DefaultVacanciesRepository
    ): VacanciesRepository

}