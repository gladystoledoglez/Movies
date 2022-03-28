package com.personal.movies.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.personal.movies.api.MovieService
import com.personal.movies.api.RetrofitServiceBuilder
import com.personal.movies.data.CurrentMovies
import com.personal.movies.data.GenresList
import com.personal.movies.data.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "920529c205ffe8ce82fd9a403b876767"
private const val IMAGE_URL_ROOT = "https://image.tmdb.org/t/p/w500/"

object MoviesRepository {
    private val moviesService: MovieService =
        RetrofitServiceBuilder(BASE_URL)
            .buildService(MovieService::class.java)

    private val moviesLiveData = MutableLiveData<List<Movie>>()

    fun getCurrentMovies(): LiveData<List<Movie>> {
        val call = moviesService.getCurrentMovies(API_KEY, "es", 1, "ES")
        call.enqueue(object : Callback<CurrentMovies> {
            override fun onResponse(call: Call<CurrentMovies>, response: Response<CurrentMovies>) {
                if (response.isSuccessful) {
                    dataMovie(response.body()?.movies ?: listOf())
                } else
                    Log.d("REPOSITORY", "Movies API error")
            }

            
            override fun onFailure(call: Call<CurrentMovies>, t: Throwable) {
                Log.d("REPOSITORY", "Movies API error")
            }
        })
        return moviesLiveData
    }

    private fun dataMovie(movies: List<Movie>) {
        val call = moviesService.getGenresList(API_KEY, "es")
        call.enqueue(object : Callback<GenresList> {
            override fun onResponse(call: Call<GenresList>, response: Response<GenresList>) {
                if (response.isSuccessful) {
                    val genres = response.body()?.genres
                    val genresMap = genres?.associateBy({ it.id }, { it.name })
                    genresMap?.let { map ->
                        movies.forEach { movie ->
                            movie.genres = movie.genreIDs.map { map[it] ?: "" }
                            movie.imageURL = IMAGE_URL_ROOT + movie.posterPath
                        }
                        moviesLiveData.value = movies
                    }
                }
            }

            override fun onFailure(call: Call<GenresList>, t: Throwable) {
            }

        })
    }


}