package com.example.popcorntime.domain.model

data class Search(
    val search: List<MovieSummary>,
    val totalResults: String,
    val response: String
)