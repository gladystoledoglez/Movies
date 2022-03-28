package com.personal.movies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MovieListActivity : AppCompatActivity() {

    private val viewModel: MovieListViewModel by viewModels()
    private var rvMovies: RecyclerView? = null
    private val adapter = MoviesListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        rvMovies = findViewById(R.id.rvMovies)
        rvMovies?.adapter = adapter
        viewModel.movies.observe(this) { adapter.submitList(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        rvMovies?.adapter = null
    }

}