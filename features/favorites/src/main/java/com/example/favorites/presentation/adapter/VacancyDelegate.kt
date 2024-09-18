package com.example.favorites.presentation.adapter

import android.view.View
import com.example.common.getAddition
import com.example.designsystem.databinding.ItemVacancyBinding
import com.example.favorites.presentation.model.FavoritesScreenItem
import com.example.favorites.presentation.model.Vacancy
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun vacancyDelegate(
    setLike: (String) -> Unit,
    unsetLike: (String) -> Unit
) = adapterDelegateViewBinding<Vacancy, FavoritesScreenItem, ItemVacancyBinding>(
    { layoutInflater, parent -> ItemVacancyBinding.inflate(layoutInflater, parent, false)  }
){
    bind {
        with(binding){
            if (item.lookingNumber != null) {
                val lookingTitleId = getAddition(
                    item.lookingNumber!!,
                    com.example.designsystem.R.string.title_vacancy_card_looking_1,
                    com.example.designsystem.R.string.title_vacancy_card_looking_2,
                    com.example.designsystem.R.string.title_vacancy_card_looking_3
                )
                tvLooking.text = context.getString(lookingTitleId, item.lookingNumber)
                tvLooking.visibility = View.VISIBLE
            } else tvLooking.visibility = View.GONE

            tvTitle.text = item.title
            tvCity.text = item.address.town
            tvCompany.text = item.company
            tvExperience.text = item.experience.previewText
            tvDate.text = context.getString(com.example.designsystem.R.string.title_published, item.publishedDate)
            btnLike.setImageResource(
                if (item.isFavorite) com.example.designsystem.R.drawable.ic_heart_like
                else com.example.designsystem.R.drawable.ic_heart
            )
            btnLike.setOnClickListener {
                if (item.isFavorite) unsetLike(item.id) else setLike(item.id)
            }
        }
    }
}