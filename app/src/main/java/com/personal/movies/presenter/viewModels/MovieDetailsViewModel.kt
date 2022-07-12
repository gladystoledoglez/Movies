package com.personal.movies.presenter.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personal.movies.R
import com.personal.movies.domain.models.MovieDetailsModel
import com.personal.movies.domain.models.MovieModel
import com.personal.movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _details = MutableLiveData<MovieDetailsModel?>()
    val details: LiveData<MovieDetailsModel?> = _details

    private val _isImageLoading = MutableLiveData(false)
    val isImageLoading: LiveData<Boolean> = _isImageLoading

    private val _imageErrorRes = MutableLiveData<Int?>(null)
    val imageErrorRes: LiveData<Int?> = _imageErrorRes

    fun getMovieDetails(movie: MovieModel?) {
        viewModelScope.launch {
            repository.getMovieDetails(movie)
                .onStart { _isImageLoading.postValue(true) }
                .onCompletion { _isImageLoading.postValue(false) }
                .catch {
                    _imageErrorRes.postValue(R.string.imageError)
                    _isImageLoading.postValue(false)
                }
                .collect { _details.postValue(it) }
        }
    }

}