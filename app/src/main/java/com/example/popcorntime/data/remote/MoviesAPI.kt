package com.example.popcorntime.data.remote

import com.example.popcorntime.data.remote.dto.MovieFullResponse
import com.example.popcorntime.data.remote.dto.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("/")
    suspend fun getMoviesBySearch(
        @Query("s") search: String,
    ): SearchResponse

    @GET("/")
    suspend fun getMovieById(
        @Query("i") search: String,
    ): MovieFullResponse

    @GET("/")
    suspend fun getMovieByTitle(
        @Query("t") search: String,
    ): MovieFullResponse
}