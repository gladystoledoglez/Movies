package com.personal.movies.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.personal.movies.databinding.ListItemMovieBinding
import com.personal.movies.domain.models.MovieModel
import com.personal.movies.presenter.viewHolders.MoviesViewHolder

class MoviesListAdapter(
    private val onItemDetails: (item: MovieModel) -> Unit
) : ListAdapter<MovieModel, MoviesViewHolder>(MovieModel.DIFF_ITEM_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MoviesViewHolder(
        ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onItemDetails
    )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }
}