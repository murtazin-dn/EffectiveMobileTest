package com.example.effectivemobiletest.di

import com.example.effectivemobiletest.data.repository.DefaultVacanciesRepository
import com.example.effectivemobiletest.data.repository.VacanciesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    internal abstract fun bindVacanciesRepository(
        vacanciesRepository: DefaultVacanciesRepository
    ): VacanciesRepository
}

