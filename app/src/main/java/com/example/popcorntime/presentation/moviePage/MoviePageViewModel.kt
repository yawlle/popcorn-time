package com.example.popcorntime.presentation.moviePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popcorntime.domain.model.MovieFull
import com.example.popcorntime.domain.use_cases.GetMoviesByIdUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

open class MoviePageViewModel @Inject constructor(
    private val getMoviesById: GetMoviesByIdUseCase
) : ViewModel() {

    private val _movieFull = MutableLiveData<MovieFull>()
    val movieFull: LiveData<MovieFull> get() = _movieFull

    private val _btnBackClicked = Channel<Boolean>()
    val btnBackClicked = _btnBackClicked.receiveAsFlow()

    private val _moviePageState = MutableStateFlow(MoviePageState())
    val moviePageState: StateFlow<MoviePageState> = _moviePageState

    fun getMovie(movieId: String) {
        viewModelScope.launch {
            try {
                _movieFull.value = getMoviesById(movieId)
                _moviePageState.update {
                    it.copy(
                        isLoading = false,
                        showMainLayout = true,
                        apiError = false
                    )
                }
            } catch (e: Exception) {
                _moviePageState.update {
                    it.copy(
                        isLoading = false,
                        showMainLayout = true,
                        apiError = true
                    )
                }
            }
        }
    }

    fun onBtnBackClick() {
        viewModelScope.launch {
            _btnBackClicked.send(true)
        }
    }
}