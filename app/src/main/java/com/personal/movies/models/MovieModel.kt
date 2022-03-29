package com.personal.movies.models

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieModel(
    val title: String? = null,
    val imageUrl: String? = null,
    val points: Double? = null,
    var genres: String? = null,
    val description: String? = null
) : Parcelable {
    companion object {
        val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.description == newItem.description
            }
        }
    }
}