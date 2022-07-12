package com.personal.movies.presenter.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.personal.movies.databinding.ListItemMovieBinding
import com.personal.movies.domain.models.MovieModel

class MoviesViewHolder(
    private val binding: ListItemMovieBinding,
    private val onItemDetails: (item: MovieModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieModel) {
        with(binding) {
            tvRatings.text = item.points.toString()
            tvName.text = item.title
        }
        itemView.setOnClickListener { onItemDetails(item) }
    }
}