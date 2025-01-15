package com.example.popcorntime.domain.use_cases

import com.example.popcorntime.data.repository.MoviesRepository
import com.example.popcorntime.domain.mapper.toMovieFull
import com.example.popcorntime.domain.model.MovieFull
import javax.inject.Inject

class GetMoviesByTitleUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(title: String): MovieFull =
        moviesRepository.getMovieByTitle(title).getOrThrow().toMovieFull()
}