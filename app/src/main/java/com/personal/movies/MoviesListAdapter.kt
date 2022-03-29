package com.personal.movies


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.personal.movies.models.MovieModel

class MoviesListAdapter(
    private val onItemDetails: (item: MovieModel) -> Unit
) : ListAdapter<MovieModel, MoviesViewHolder>(MovieModel.DIFF_ITEM_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_list, parent, false)
        return MoviesViewHolder(view, onItemDetails)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }
}