package com.example.search.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.result.Result
import com.example.search.data.model.VacancyModel
import com.example.search.domain.GetVacanciesUseCase
import com.example.search.domain.SetFavoriteUseCase
import com.example.search.domain.UnsetFavoriteUseCase
import com.example.search.presentation.adapter.BottomItem
import com.example.search.presentation.adapter.HeaderItemCollapsed
import com.example.search.presentation.adapter.HeaderItemExpanded
import com.example.search.presentation.adapter.SearchScreenItem
import com.example.search.presentation.model.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val setFavoriteUseCase: SetFavoriteUseCase,
    private val unsetFavoriteUseCase: UnsetFavoriteUseCase
) : ViewModel() {

    private val isExpanded = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<SearchScreenState> =
        isExpanded
            .flatMapLatest { isExpanded ->
                getVacanciesUseCase.execute() // Suspend function called within flow context
                    .map { data ->
                        when (data) {
                            is Result.Error -> SearchScreenState.Error(data.message ?: "")
                            is Result.Success -> {
                                val vacancies = if (isExpanded) {
                                    buildVacanciesExpanded(data.value.vacancies)
                                } else {
                                    buildVacanciesCollapsed(data.value.offers, data.value.vacancies)
                                }
                                SearchScreenState.Search(vacancies)
                            }
                        }
                    }
            }
            .onStart { emit(SearchScreenState.Loading) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SearchScreenState.Loading
            )

    private fun buildVacanciesCollapsed(
        offers: List<com.example.search.data.model.OfferModel>,
        vacancies: List<VacancyModel>
    ): List<SearchScreenItem> {
        return mutableListOf<SearchScreenItem>().apply {
            add(HeaderItemCollapsed(offers.map { it.toUi() }))
            addAll(vacancies.take(3).map { it.toUi() })
            add(BottomItem(vacancies.size))
        }
    }
    private fun buildVacanciesExpanded(
        vacancies: List<VacancyModel>
    ): List<SearchScreenItem> {
        return mutableListOf<SearchScreenItem>().apply {
            add(HeaderItemExpanded(vacancies.size))
            addAll(vacancies.map { it.toUi() })
        }
    }

    fun setFavorite(id: String) {
        viewModelScope.launch {
            setFavoriteUseCase.execute(id)
        }
    }

    fun unsetFavorite(id: String) {
        viewModelScope.launch {
            unsetFavoriteUseCase.execute(id)
        }
    }

    fun changeExpandState(){
        isExpanded.value = !isExpanded.value
    }
}