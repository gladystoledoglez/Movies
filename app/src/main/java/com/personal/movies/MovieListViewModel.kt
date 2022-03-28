package com.personal.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.personal.movies.data.Movie
import com.personal.movies.repositories.MoviesRepository

class MovieListViewModel : ViewModel() {
    val movies: LiveData<List<Movie>> get() = MoviesRepository.getCurrentMovies()
}