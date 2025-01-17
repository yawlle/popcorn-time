package com.example.popcorntime.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieSummaryResponse(
    @SerializedName("Title")
    val title: String?,
    @SerializedName("Year")
    val year: String?,
    @SerializedName("imdbID")
    val imdbID: String?,
    @SerializedName("Type")
    val type: String?,
    @SerializedName("Poster")
    val poster: String?,
    val imdbScore: String?
)