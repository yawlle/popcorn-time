package com.example.popcorntime.data.repository

import com.example.popcorntime.data.remote.MoviesAPI
import com.example.popcorntime.data.remote.dto.MovieFullResponse
import com.example.popcorntime.data.remote.dto.MovieSummaryResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val movieAPI: MoviesAPI
) {

    suspend fun getDetailedMoviesBySearch(search: String): Result<List<MovieSummaryResponse>> {
        return try {
            val searchResult = movieAPI.getMoviesBySearch(search)

            val movieSummaries = searchResult.search ?: emptyList()

            val detailedMovies = coroutineScope {
                movieSummaries.map { movie ->
                    async {
                        movie.imdbID?.let { id ->
                            movieAPI.getMovieById(id)
                        }
                    }
                }.awaitAll()
            }

            val combinedMovies = movieSummaries.mapIndexed { index, summary ->
                val fullMovie = detailedMovies[index]
                summary.copy(
                    title = fullMovie?.title ?: summary.title,
                    year = fullMovie?.year ?: summary.year,
                    poster = fullMovie?.poster ?: summary.poster,
                    imdbScore = fullMovie?.imdbRating ?: summary.imdbScore
                )
            }

            Result.success(combinedMovies)
        } catch (e: Exception) {
            Result.failure(e)
        }
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
