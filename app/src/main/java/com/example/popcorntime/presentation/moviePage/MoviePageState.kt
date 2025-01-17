package com.example.popcorntime.presentation.moviePage

data class MoviePageState(
    var isLoading: Boolean = true,
    val showMainLayout: Boolean = false,
    var apiError: Boolean = false,
)