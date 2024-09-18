package com.example.database.di

import com.example.database.HhDatabase
import com.example.database.dao.VacancyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun provideVacancyDao(
        database: HhDatabase
    ): VacancyDao = database.getVacancyDao()

}