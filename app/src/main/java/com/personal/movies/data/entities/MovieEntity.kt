package com.personal.movies.data.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieEntity(
    @SerializedName("title") val title: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val points: Double?,
    @SerializedName("genre_ids") val genreIDs: List<Int>?,
    @SerializedName("overview") val description: String?
) : Serializable

