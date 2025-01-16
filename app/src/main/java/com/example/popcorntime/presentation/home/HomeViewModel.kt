package com.example.popcorntime.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popcorntime.data.di.IoDispatcher
import com.example.popcorntime.domain.model.MovieSummary
import com.example.popcorntime.domain.use_cases.GetMoviesBySearchUseCase
import com.example.popcorntime.presentation.common.ScreenState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

open class HomeViewModel @Inject constructor(
    private val getMoviesBySearch: GetMoviesBySearchUseCase,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private var movies: List<MovieSummary> = emptyList()

    private val _filteredMoviesList = MutableStateFlow<List<MovieSummary>>(emptyList())
    val filteredMoviesList = _filteredMoviesList.asStateFlow()

    private val _searchValue = MutableStateFlow("")
    val searchValue = _searchValue.asStateFlow()

    private val _showNoResults = MutableStateFlow(false)
    val showNoResults = _showNoResults.asStateFlow()

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    init {
        getHome()
    }

    fun changeSearchValue(text: String) {
        _searchValue.value = text
    }

    private fun getHome() {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch(coroutineDispatcher) {
            try {
                val movies = getMoviesBySearch("Harry Potter")
                _filteredMoviesList.value = movies.search
                _screenState.value = ScreenState.Content
            } catch (e: Exception) {
                _screenState.value = ScreenState.Error(e.message.orEmpty())
            }
        }
    }

    fun onSearchValueChange(value: String) {
        _screenState.value = ScreenState.Loading
        _searchValue.value = value
        viewModelScope.launch(coroutineDispatcher) {
            try {
                _filteredMoviesList.value = getMoviesBySearch(value).search
                _screenState.value = ScreenState.Content
                _showNoResults.value = _filteredMoviesList.value.isEmpty()
            } catch (e: Exception) {
                _screenState.value = ScreenState.Error(e.message.orEmpty())
            }
        }
    }
}