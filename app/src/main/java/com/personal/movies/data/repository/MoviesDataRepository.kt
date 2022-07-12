package com.personal.movies.data.repository

import com.personal.movies.data.mappers.toModelList
import com.personal.movies.data.remote.MoviesRemoteDataSource
import com.personal.movies.domain.models.MovieDetailsModel
import com.personal.movies.domain.models.MovieModel
import com.personal.movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.map

class MoviesDataRepository(
    private val remoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {
    override fun getMovies() = remoteDataSource.getMovies().map { it.movies.toModelList() }
    override fun getMovieDetails(movie: MovieModel?) = remoteDataSource.getGenres().map { list ->
        val genres = list.genres.filter { movie?.genreIDs?.contains(it.id) == true }
        MovieDetailsModel(
            imageUrl = movie?.imageUrl,
            genres = genres.joinToString(", ") { it.name },
            description = movie?.description
        )
    }
}