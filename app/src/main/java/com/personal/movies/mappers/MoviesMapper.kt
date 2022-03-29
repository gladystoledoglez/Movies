package com.personal.movies.mappers

import com.personal.movies.data.Movie
import com.personal.movies.models.MovieModel

fun Movie.toModel() = MovieModel(
    title = title,
    imageUrl = imageUrl,
    points = points,
    genres = getGenreString(),
    description = description
)

fun List<Movie>.toModelList() = map { it.toModel() }
