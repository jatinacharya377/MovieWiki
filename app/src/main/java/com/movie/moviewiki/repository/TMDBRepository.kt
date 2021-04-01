package com.movie.moviewiki.repository

import com.movie.moviewiki.api.TMDBService
import com.movie.moviewiki.di.DaggerApiComponent
import com.movie.moviewiki.model.MovieDetails
import com.movie.moviewiki.model.popular.PopularMovieResults
import com.movie.moviewiki.model.trending.TrendingMovieResults
import io.reactivex.Single
import javax.inject.Inject

class TMDBRepository {

    @Inject
    lateinit var tmdbService: TMDBService

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getMovieDetails(id: String, api_key: String): Single<MovieDetails> {

        return tmdbService.api.getMovieDetails(id, api_key)
    }

    fun getPopularMovies(api_key: String): Single<PopularMovieResults> {

        return tmdbService.api.getPopularMovies(api_key)
    }

    fun getTrendingMovies(api_key: String, pageNo: Int): Single<TrendingMovieResults> {

        return tmdbService.api.getTrendingMovies(api_key, pageNo)
    }
}