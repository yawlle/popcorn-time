package com.example.popcorntime.presentation.moviePage

import androidx.lifecycle.ViewModel
import com.example.popcorntime.domain.use_cases.GetMoviesByIdUseCase
import javax.inject.Inject

open class MoviePageViewModel @Inject constructor(
    private val getMoviesById: GetMoviesByIdUseCase,
) : ViewModel() {

}