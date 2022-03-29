package com.personal.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.personal.movies.databinding.ActivityDetailsMovieBinding
import com.personal.movies.models.MovieModel

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val item = intent.getParcelableExtra<MovieModel>("ITEM")
        initializeComponentsFrom(item)
    }

    private fun initializeComponentsFrom(item: MovieModel?) {
        with(binding) {
            Glide.with(this@MovieDetailsActivity).load(item?.imageUrl).into(ivPoster)
            tbGenre.title = item?.genres
            tvDescription.text = item?.description
        }
    }
}