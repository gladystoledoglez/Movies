package com.personal.movies.data.remote

import com.personal.movies.data.entities.CurrentMoviesEntity
import com.personal.movies.data.entities.GenresListEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDatabaseService {
    @GET("movie/now_playing")
    suspend fun getCurrentMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): CurrentMoviesEntity

    @GET("genre/movie/list")
    suspend fun getGenresList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): GenresListEntity
}