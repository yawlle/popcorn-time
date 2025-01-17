package com.example.popcorntime.domain.model

data class MovieSummary(
    val title: String,
    val year: String,
    val imdbID: String,
    val type: String,
    val poster: String,
    val imdbScore: String
)