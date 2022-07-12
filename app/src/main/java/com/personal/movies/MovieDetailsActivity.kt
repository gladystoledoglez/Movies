package com.personal.movies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.personal.movies.databinding.ActivityMovieDetailsBinding
import com.personal.movies.domain.models.MovieDetailsModel
import com.personal.movies.domain.models.MovieModel
import com.personal.movies.presenter.viewModels.MovieDetailsViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private val viewModel: MovieDetailsViewModel by viewModel(
        clazz = MovieDetailsViewModel::class.java.kotlin
    )
    private val movie by lazy { intent.getParcelableExtra<MovieModel>("ITEM") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObservers()
    }

    private fun setObservers() {
        with(viewModel) {
            details.observe(this@MovieDetailsActivity) {
                fillComponentsFrom(it)
                binding.ivPoster.isVisible = true
            }
            isImageLoading.observe(this@MovieDetailsActivity) {
                binding.pbMovieImage.isVisible = it ?: false
            }
            imageErrorRes.observe(this@MovieDetailsActivity) { resource ->
                binding.ivPoster.isVisible = false
                resource?.let {
                    Toast.makeText(
                        this@MovieDetailsActivity, getString(it), Toast.LENGTH_SHORT
                    ).show()
                }
            }
            getMovieDetails(movie)
        }
    }

    private fun fillComponentsFrom(details: MovieDetailsModel?) {
        with(binding) {
            pbMovieImage.visibility = View.VISIBLE
            Picasso.get()
                .load(details?.imageUrl)
                .into(ivPoster, object : Callback {
                    override fun onSuccess() {
                        pbMovieImage.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        pbMovieImage.visibility = View.GONE
                    }
                })
            tbGenre.text = details?.genres
            tvDescription.text = details?.description
        }
    }
}