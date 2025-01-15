package com.example.popcorntime.data.repository

import com.example.popcorntime.data.remote.MoviesAPI
import com.example.popcorntime.data.remote.dto.MovieFullResponse
import com.example.popcorntime.data.remote.dto.SearchResponse
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val movieAPI: MoviesAPI
) {

    suspend fun getMoviesBySearch(search: String): Result<SearchResponse> =
        try {
            Result.success(movieAPI.getMoviesBySearch(search))
        } catch (e: Exception) {
            Result.failure(e)
        }

    suspend fun getMovieById(id: String): Result<MovieFullResponse> =
        try {
            Result.success(movieAPI.getMovieById(id))
        } catch (e: Exception) {
            Result.failure(e)
        }

    suspend fun getMovieByTitle(title: String): Result<MovieFullResponse> =
        try {
            Result.success(movieAPI.getMovieByTitle(title))
        } catch (e: Exception) {
            Result.failure(e)
        }
}
