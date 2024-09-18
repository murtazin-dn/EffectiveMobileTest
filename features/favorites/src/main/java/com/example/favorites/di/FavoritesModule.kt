package com.example.favorites.di

import com.example.favorites.data.repository.DefaultVacanciesRepository
import com.example.favorites.data.repository.VacanciesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoritesModule {

    @Binds
    internal abstract fun bindVacanciesRepository(
        vacanciesRepository: DefaultVacanciesRepository
    ): VacanciesRepository

}