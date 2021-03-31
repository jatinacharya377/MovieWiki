package com.movie.moviewiki.di

import com.movie.moviewiki.api.MovieDbApi
import com.movie.moviewiki.api.MoviesService
import com.movie.moviewiki.repository.MovieRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val baseUrl = "https://api.themoviedb.org/3/"

    @Provides
    fun getMovieDbApi(): MovieDbApi {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MovieDbApi::class.java)
    }

    @Provides
    fun getMoviesService(): MoviesService {

        return MoviesService()
    }

    @Provides
    fun getMovieRepository(): MovieRepository {

        return MovieRepository()
    }
}