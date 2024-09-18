package com.example.favorites.presentation.adapter

import com.example.common.getAddition
import com.example.favorites.databinding.ItemFavoritesHeaderBinding
import com.example.favorites.presentation.model.FavoritesHeaderItem
import com.example.favorites.presentation.model.FavoritesScreenItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun headerDelegate() = adapterDelegateViewBinding<FavoritesHeaderItem, FavoritesScreenItem, ItemFavoritesHeaderBinding>(
    { layoutInflater, parent -> ItemFavoritesHeaderBinding.inflate(layoutInflater, parent, false) }
){
    bind {
        val vacanciesCountAddition = getAddition(
            item.vacanciesCount,
            com.example.designsystem.R.string.title_vacancy_count_1,
            com.example.designsystem.R.string.title_vacancy_count_2,
            com.example.designsystem.R.string.title_vacancy_count_3
        )
        binding.tvVacanciesCount.text = context.getString(vacanciesCountAddition, item.vacanciesCount)
    }
}