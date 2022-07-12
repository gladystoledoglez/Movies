package com.personal.movies.presenter.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personal.movies.domain.models.MovieModel
import com.personal.movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieListViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieModel>>()
    val movies: LiveData<List<MovieModel>> get() = _movies

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun getMovies() {
        viewModelScope.launch {
            repository.getMovies()
                .onStart { _isLoading.postValue(true) }
                .catch {
                    _error.postValue(it.message.orEmpty())
                    _isLoading.postValue(false)
                }
                .onCompletion { _isLoading.postValue(false) }
                .collect { _movies.postValue(it) }
        }
    }
}