package com.personal.movies.presenter.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.personal.movies.MovieDetailsActivity
import com.personal.movies.databinding.FragmentMoviesListBinding
import com.personal.movies.domain.models.MovieModel
import com.personal.movies.presenter.adapters.MoviesListAdapter
import com.personal.movies.presenter.viewModels.MovieListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListFragment : Fragment() {

    private lateinit var binding: FragmentMoviesListBinding
    private val adapter by lazy { MoviesListAdapter(::onItemDetails) }
    private val viewModel: MovieListViewModel by viewModel(clazz = MovieListViewModel::class.java.kotlin)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMoviesListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        initObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvMovies.adapter = null
    }

    private fun initComponents() {
        with(binding) {
            rvMovies.adapter = adapter
        }
    }

    private fun initObservers() {
        with(viewModel) {
            isLoading.observe(viewLifecycleOwner) {
                binding.pbListMovies.isVisible = it ?: false
            }
            error.observe(viewLifecycleOwner) { message ->
                message?.takeIf { it.isNotBlank() }
                    ?.let {
                        android.widget.Toast.makeText(
                            context,
                            it,
                            android.widget.Toast.LENGTH_SHORT
                        ).show()
                    }
            }
            movies.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
            getMovies()
        }

    }

    private fun onItemDetails(movie: MovieModel) {
        val intent = Intent(this.context, MovieDetailsActivity::class.java)
        intent.putExtra("ITEM", movie)
        startActivity(intent)
    }
}