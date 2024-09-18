package com.example.favorites.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.result.Result
import com.example.favorites.domain.GetVacanciesUseCase
import com.example.favorites.domain.SetFavoriteUseCase
import com.example.favorites.domain.UnsetFavoriteUseCase
import com.example.favorites.presentation.model.FavoritesHeaderItem
import com.example.favorites.presentation.model.FavoritesScreenItem
import com.example.favorites.presentation.model.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FavoritesViewModel @Inject constructor(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val setFavoriteUseCase: SetFavoriteUseCase,
    private val unsetFavoriteUseCase: UnsetFavoriteUseCase
) : ViewModel() {

    private val isFavorite = MutableStateFlow(false)
    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<FavoritesScreenState> =
        isFavorite
            .flatMapLatest { isFavorite ->
                getVacanciesUseCase.execute() // This returns Flow<Result<List<VacancyModel>>>
                    .map { data ->
                        when (data) {
                            is Result.Error -> FavoritesScreenState.Error(data.message ?: "")
                            is Result.Success -> {
                                val vacancies = mutableListOf<FavoritesScreenItem>()
                                vacancies.add(FavoritesHeaderItem(data.value.size))
                                vacancies.addAll(data.value.map { it.toUi() })
                                FavoritesScreenState.Favorites(vacancies)
                            }
                        }
                    }
            }
        .onStart { emit(FavoritesScreenState.Loading) } // Emits loading state initially
        .stateIn(
            scope = viewModelScope,  // Ensure it's executed within the ViewModel's coroutine scope
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FavoritesScreenState.Loading // Initial state
        )



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
}