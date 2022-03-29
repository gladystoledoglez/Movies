package com.personal.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.personal.movies.models.MovieModel
import com.personal.movies.repositories.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    private val _movies = MutableLiveData<List<MovieModel>>()
    val movies: LiveData<List<MovieModel>> get() = _movies

    fun loadMovies() {
        CoroutineScope(Dispatchers.Main).launch {
            _movies.value = MoviesRepository.getMovies()
        }
    }
}