package com.example.popcorntime.domain.use_cases

import com.example.popcorntime.data.repository.MoviesRepository
import com.example.popcorntime.domain.mapper.toMovieSummary
import com.example.popcorntime.domain.model.MovieSummary
import javax.inject.Inject

class GetMoviesBySearchUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(search: String): List<MovieSummary> =
        moviesRepository.getDetailedMoviesBySearch(search).getOrThrow().map { it.toMovieSummary() }

}