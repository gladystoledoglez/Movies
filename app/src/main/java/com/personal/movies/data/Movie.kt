package com.personal.movies.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    @SerializedName("title") val title: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val points: Double?,
    @SerializedName("genre_ids") val genreIDs: List<Int>?,
    @SerializedName("overview") val description: String?
) :
    Serializable {
    var imageUrl: String = ""
    var genres: List<String>? = mutableListOf()

    fun getGenreString(): String {
        val stringBuilder = StringBuilder()
        genres?.forEach { genre ->
            stringBuilder.append(genre)
            stringBuilder.append(", ")
        }
        return stringBuilder.toString().removeSuffix(", ")
    }

}

