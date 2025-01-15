package com.example.popcorntime.domain.use_cases

import com.example.popcorntime.data.repository.MoviesRepository
import com.example.popcorntime.domain.mapper.toMovieFull
import com.example.popcorntime.domain.model.MovieFull
import javax.inject.Inject

class GetMoviesByIdUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(id: String): MovieFull =
        moviesRepository.getMovieById(id).getOrThrow().toMovieFull()

}