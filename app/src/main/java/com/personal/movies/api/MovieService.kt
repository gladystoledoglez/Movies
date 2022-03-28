package com.personal.movies.api

import com.personal.movies.data.CurrentMovies
import com.personal.movies.data.GenresList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/now_playing")
    fun getCurrentMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): Call<CurrentMovies>

    @GET("genre/movie/list")
    fun getGenresList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): Call<GenresList>
}