package com.personal.movies.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.personal.movies.data.remote.MoviesRemoteDataSource
import com.personal.movies.data.remote.TheMovieDatabaseService
import com.personal.movies.data.repository.MoviesDataRepository
import com.personal.movies.domain.repository.MoviesRepository
import com.personal.movies.presenter.viewModels.MovieDetailsViewModel
import com.personal.movies.presenter.viewModels.MovieListViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun Scope.getRetrofit() = get<Retrofit>()

object MainModule {
    private val networkModule = module {

        fun provideBaseUrl() = "https://api.themoviedb.org/3/"

        fun provideGson() = GsonBuilder().create()

        fun provideOkHttp() = OkHttpClient.Builder().build()

        fun provideRetrofit(baseUrl: String, okHttp: OkHttpClient, gson: Gson) = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        single { provideBaseUrl() }
        single { provideGson() }
        single { provideOkHttp() }
        single { provideRetrofit(get(), get(), get()) }
        single { getRetrofit().create(TheMovieDatabaseService::class.java) }
    }

    private val dataModule = module {
        single { MoviesRemoteDataSource(get()) }
        single<MoviesRepository> { MoviesDataRepository(get()) }
    }

    private val viewModelsModule = module {
        viewModel { MovieListViewModel(get()) }
        viewModel { MovieDetailsViewModel(get()) }
    }

    fun init() = loadKoinModules(
        listOf(networkModule, dataModule, viewModelsModule)
    )
}