package com.personal.movies.domain.models

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieModel(
    val points: Double? = null,
    val title: String? = null,
    val imageUrl: String? = null,
    val genreIDs: List<Int>? = null,
    val description: String? = null
) : Parcelable {

    companion object {
        val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.title == newItem.title
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}