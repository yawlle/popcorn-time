package com.example.popcorntime.domain.mapper

import com.example.popcorntime.data.remote.dto.MovieFullResponse
import com.example.popcorntime.data.remote.dto.MovieSummaryResponse
import com.example.popcorntime.data.remote.dto.RatingResponse
import com.example.popcorntime.data.remote.dto.SearchResponse
import com.example.popcorntime.domain.model.MovieFull
import com.example.popcorntime.domain.model.MovieSummary
import com.example.popcorntime.domain.model.Rating
import com.example.popcorntime.domain.model.Search

fun RatingResponse.toRating() =
    Rating(
        source = source.orEmpty(),
        value = value.orEmpty()
    )

fun MovieFullResponse.toMovieFull() =
    MovieFull(
        title = title.orEmpty(),
        year = year.orEmpty(),
        rated = rated.orEmpty(),
        released = released.orEmpty(),
        runtime = runtime.orEmpty(),
        genre = genre.orEmpty(),
        director = director.orEmpty(),
        writer = writer.orEmpty(),
        actors = actors.orEmpty(),
        plot = plot.orEmpty(),
        language = language.orEmpty(),
        country = country.orEmpty(),
        awards = awards.orEmpty(),
        poster = poster.orEmpty(),
        ratings = ratings?.map {
            it.toRating()
        }.orEmpty(),
        metaScore = metascore.orEmpty(),
        imdbRating = imdbRating.orEmpty(),
        imdbVotes = imdbVotes.orEmpty(),
        imdbID = imdbID.orEmpty(),
        type = type.orEmpty(),
        dvd = dvd.orEmpty(),
        boxOffice = boxOffice.orEmpty(),
        production = production.orEmpty(),
        website = website.orEmpty(),
        response = response.orEmpty()
    )


fun MovieSummaryResponse.toMovieSummary() =
    MovieSummary(
        title = title.orEmpty(),
        year = year.orEmpty(),
        imdbID = imdbID.orEmpty(),
        type = type.orEmpty(),
        poster = poster.orEmpty()
    )

fun SearchResponse.toSearch() =
    Search(
        search = search?.map {
            it.toMovieSummary()
        }.orEmpty(),
        totalResults = totalResults.orEmpty(),
        response = response.orEmpty()
    )