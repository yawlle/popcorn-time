package com.example.popcorntime.domain.use_cases

import com.example.popcorntime.data.repository.MoviesRepository
import com.example.popcorntime.domain.mapper.toSearch
import com.example.popcorntime.domain.model.Search
import javax.inject.Inject

class GetMoviesBySearchUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(search: String): Search =
        moviesRepository.getMoviesBySearch(search).getOrThrow().toSearch()

}