package com.personal.movies.domain.repository

import com.personal.movies.domain.models.MovieDetailsModel
import com.personal.movies.domain.models.MovieModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMovies(): Flow<List<MovieModel>>
    fun getMovieDetails(movie: MovieModel?): Flow<MovieDetailsModel>
}