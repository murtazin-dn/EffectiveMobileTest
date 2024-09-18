package com.example.search.presentation.adapter

import android.text.Editable.Factory
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.getAddition
import com.example.search.databinding.ItemFastFilterBinding
import com.example.search.databinding.ItemSearchBottomBinding
import com.example.search.databinding.ItemSearchHeaderBinding
import com.example.search.databinding.ItemSearchHeaderExpandedBinding
import com.example.search.presentation.model.Offer
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


internal fun headerCollapsedDelegate(onCardClick: (String) -> Unit) =
    adapterDelegateViewBinding<HeaderItemCollapsed, SearchScreenItem, ItemSearchHeaderBinding>(
        { layoutInflater, parent -> ItemSearchHeaderBinding.inflate(layoutInflater, parent, false).apply {
            recycler.adapter = ListDelegationAdapter(fastFilterDelegate(onCardClick))
            recycler.layoutManager = LinearLayoutManager(this.root.context, LinearLayoutManager.HORIZONTAL, false)
        } }
){
    bind{
        (binding.recycler.adapter as ListDelegationAdapter<List<Offer>>).apply {
            items = item.filters
            notifyDataSetChanged()
        }
    }
}
internal fun headerExpandedDelegate(onBack: () -> Unit) =
    adapterDelegateViewBinding<HeaderItemExpanded, SearchScreenItem, ItemSearchHeaderExpandedBinding>(
        { layoutInflater, parent -> ItemSearchHeaderExpandedBinding.inflate(layoutInflater, parent, false) }
){
    bind{
        binding.btnBack.setOnClickListener { onBack() }
        val vacanciesCountAddition = getAddition(
                item.vacanciesCount,
        com.example.designsystem.R.string.title_vacancy_count_1,
        com.example.designsystem.R.string.title_vacancy_count_2,
        com.example.designsystem.R.string.title_vacancy_count_3
        )
        binding.tvVacanciesCount.text = context.getString(vacanciesCountAddition, item.vacanciesCount)
    }
}

internal fun bottomDelegate(onClick: () -> Unit) = adapterDelegateViewBinding<BottomItem, SearchScreenItem, ItemSearchBottomBinding>(
    { layoutInflater, parent -> ItemSearchBottomBinding.inflate(layoutInflater, parent, false) }
){
    bind {
        binding.btnMoreVacancies.text = Factory.getInstance().newEditable("Еще ${item.vacanciesCount} вакансии")
        binding.btnMoreVacancies.setOnClickListener { onClick() }
    }
}


internal fun fastFilterDelegate(onCardClick: (String) -> Unit) = adapterDelegateViewBinding<Offer, Offer, ItemFastFilterBinding>(
    { layoutInflater, parent -> ItemFastFilterBinding.inflate(layoutInflater, parent, false) }
){
    fun setIcon(iconColor: IconColor? = null){
        iconColor?.let {
            binding.icon.apply {
                setImageResource(iconColor.resId)
                setColorFilter(resources.getColor(iconColor.iconColorId))
            }
            binding.iconCircle.apply {
                setCardBackgroundColor(resources.getColor(iconColor.iconBackgroundColorId))
                visibility = View.VISIBLE
            }
        } ?: run {
            binding.iconCircle.visibility = View.GONE
        }
    }
    bind {
        val iconColor = when(item.id){
            "near_vacancies" -> IconColor(
                com.example.designsystem.R.drawable.ic_location,
                com.example.designsystem.R.color.blue,
                com.example.designsystem.R.color.dark_blue)
            "level_up_resume" -> IconColor(
                com.example.designsystem.R.drawable.ic_star,
                com.example.designsystem.R.color.green,
                com.example.designsystem.R.color.dark_green)
            "temporary_job" -> IconColor(
                com.example.designsystem.R.drawable.ic_list,
                com.example.designsystem.R.color.green,
                com.example.designsystem.R.color.dark_green)
            else -> null
        }
        setIcon(iconColor)
        binding.description.text = item.title
        if (item.buttonText == null) {
            binding.description.maxLines = 3
            binding.button.visibility = View.GONE
        } else {
            binding.description.maxLines = 2
            binding.button.text = item.buttonText
            binding.button.visibility = View.VISIBLE
        }
    }

    binding.fastFilterCard.setOnClickListener { onCardClick(item.link) }
}

private data class IconColor(
    val resId: Int,
    val iconColorId: Int,
    val iconBackgroundColorId: Int

)

internal interface SearchScreenItem

internal data class HeaderItemCollapsed(
    val filters: List<Offer>
): SearchScreenItem

internal data class HeaderItemExpanded(
    val vacanciesCount: Int
): SearchScreenItem

internal data class BottomItem(
    val vacanciesCount: Int
): SearchScreenItem

internal data class FastFilterItem(
    val id: String? = null,
    val title: String,
    val link: String,
    val buttonText: String? = null
) : FastFilter

internal interface FastFilter

