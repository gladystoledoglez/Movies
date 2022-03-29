package com.personal.movies

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.personal.movies.models.MovieModel

class MoviesViewHolder(
    itemView: View,
    private val onItemDetails: (item: MovieModel) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: MovieModel) {
        val rating = itemView.findViewById<TextView>(R.id.tvRatings)
        val name = itemView.findViewById<TextView>(R.id.tvName)
        rating.text = item.points.toString()
        name.text = item.title
        itemView.setOnClickListener { onItemDetails(item) }
    }
}