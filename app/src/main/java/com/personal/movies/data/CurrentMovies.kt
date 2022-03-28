package com.personal.movies.data

import com.google.gson.annotations.SerializedName

data class CurrentMovies(@SerializedName("results") val movies: List<Movie>){

}