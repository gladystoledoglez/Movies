package com.personal.movies

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.personal.movies.models.MovieModel

class MovieListActivity : AppCompatActivity() {

    private val viewModel: MovieListViewModel by viewModels()
    private var rvMovies: RecyclerView? = null
    private val adapter = MoviesListAdapter(::onItemDetails)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        rvMovies = findViewById(R.id.rvMovies)
        rvMovies?.adapter = adapter
        viewModel.movies.observe(this) { adapter.submitList(it) }
        viewModel.loadMovies()
    }

    override fun onDestroy() {
        super.onDestroy()
        rvMovies?.adapter = null
    }

    private fun onItemDetails(item: MovieModel) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("ITEM", item)
        startActivity(intent)
    }

}