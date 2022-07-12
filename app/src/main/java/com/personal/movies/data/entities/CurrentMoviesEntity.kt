package com.personal.movies.data.entities

import com.google.gson.annotations.SerializedName

data class CurrentMoviesEntity(@SerializedName("results") val movies: List<MovieEntity>)