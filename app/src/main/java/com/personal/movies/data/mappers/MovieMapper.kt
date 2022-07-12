package com.personal.movies.data.mappers

import com.personal.movies.data.entities.MovieEntity
import com.personal.movies.domain.models.MovieModel

fun MovieEntity.toModel() = MovieModel(
    title = title,
    points = points,
    imageUrl = "https://image.tmdb.org/t/p/w500/$posterPath",
    genreIDs = genreIDs,
    description = description,
)

fun List<MovieEntity>.toModelList() = map { it.toModel() }
