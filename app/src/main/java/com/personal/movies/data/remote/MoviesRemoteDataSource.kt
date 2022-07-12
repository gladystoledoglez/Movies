package com.personal.movies.data.remote

import com.personal.movies.data.entities.CurrentMoviesEntity
import com.personal.movies.data.entities.GenresListEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRemoteDataSource(private val service: TheMovieDatabaseService) {

    fun getMovies(): Flow<CurrentMoviesEntity> = flow {
        emit(service.getCurrentMovies(API_KEY, "es", 1, "ES"))
    }

    fun getGenres(): Flow<GenresListEntity> = flow {
        emit(service.getGenresList(API_KEY, "es"))
    }

    companion object {
        private const val API_KEY = "920529c205ffe8ce82fd9a403b876767"
    }
}