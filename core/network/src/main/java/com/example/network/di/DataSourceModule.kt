package com.example.network.di

import com.example.network.datasource.VacanciesDataSource
import com.example.network.datasource.VacanciesOfflineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    internal abstract fun provideVacanciesDataSource(
        vacanciesOfflineDataSource: VacanciesOfflineDataSource
    ): VacanciesDataSource
}