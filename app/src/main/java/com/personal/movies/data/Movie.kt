package com.personal.movies.data

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val points: Double,
    @SerializedName("genre_ids") val genreIDs: List<Int>,
    @SerializedName("overview") val description: String
) :
    Serializable {
    var imageURL: String = ""
    var genres: List<String> = mutableListOf()

    fun getGenreString(): String {
        val stringBuilder = StringBuilder()
        for (genre in genres) {
            stringBuilder.append(genre)
            stringBuilder.append(", ")
        }
        return stringBuilder.toString().removeSuffix(", ")
    }

    companion object {
        val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.description == newItem.description
            }

        }
    }
}

