package com.example.favorites.presentation.ui

import com.example.favorites.presentation.model.FavoritesScreenItem

internal sealed interface FavoritesScreenState {
    data object Loading: FavoritesScreenState
    data class Error(val message: String?): FavoritesScreenState
    data class Favorites(val data: List<FavoritesScreenItem>): FavoritesScreenState
}