package com.example.search.presentation.ui

import com.example.search.presentation.adapter.SearchScreenItem


internal sealed interface SearchScreenState {
    data object Loading: SearchScreenState
    data class Error(val message: String): SearchScreenState
    data class Search(val data: List<SearchScreenItem>): SearchScreenState
}