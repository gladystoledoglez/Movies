package com.personal.movies.repositories

import com.personal.movies.api.MovieService
import com.personal.movies.api.RetrofitServiceBuilder
import com.personal.movies.data.Genre
import com.personal.movies.data.Movie
import com.personal.movies.mappers.toModelList
import com.personal.movies.models.MovieModel
import retrofit2.awaitResponse

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "920529c205ffe8ce82fd9a403b876767"
private const val IMAGE_URL_ROOT = "https://image.tmdb.org/t/p/w500/"

object MoviesRepository {
    private val moviesService: MovieService = RetrofitServiceBuilder(BASE_URL).buildService(
        MovieService::class.java
    )

    suspend fun getMovies(): List<MovieModel> {
        val moviesRequest = moviesService.getCurrentMovies(API_KEY, "es", 1, "ES")
        val moviesResponse = moviesRequest.awaitResponse()
        if (moviesResponse.isSuccessful) {
            val movies: List<Movie>? = moviesResponse.body()?.movies
            val genresRequest = moviesService.getGenresList(API_KEY, "es")
            val genreResponse = genresRequest.awaitResponse()
            if (genreResponse.isSuccessful) {
                val genres: List<Genre>? = genreResponse.body()?.genres
                val genresMap = genres?.associateBy({ it.id }, { it.name })
                genresMap?.let { map ->
                    movies?.forEach { movie ->
                        movie.genres = movie.genreIDs?.map { map[it].orEmpty() }
                        movie.imageUrl = IMAGE_URL_ROOT + movie.posterPath
                    }
                    return movies?.toModelList().orEmpty()
                }
            }
        }
        return emptyList()
    }
}