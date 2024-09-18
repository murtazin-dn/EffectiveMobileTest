package com.example.favorites.presentation.model

internal interface FavoritesScreenItem {
}

internal data class FavoritesHeaderItem(
    val vacanciesCount: Int
): FavoritesScreenItem