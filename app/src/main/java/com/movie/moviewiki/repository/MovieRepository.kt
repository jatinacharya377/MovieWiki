package com.movie.moviewiki.repository

import com.movie.moviewiki.api.MovieDbApi
import com.movie.moviewiki.api.MoviesService
import com.movie.moviewiki.model.PopularMovieResults
import com.movie.moviewiki.model.TrendingMovieResults
import io.reactivex.Single

class MovieRepository(private val api_key: String) {

    private lateinit var movieDbApi: MovieDbApi
    private lateinit var moviesService: MoviesService

    fun createApi() {

        moviesService = MoviesService()
        movieDbApi = moviesService.getRetrofit().create(MovieDbApi::class.java)
    }

    fun getPopularMovies(): Single<PopularMovieResults> {

        return movieDbApi.getPopularMovies(api_key)
    }

    fun getTrendingMovies(page_no: Int): Single<TrendingMovieResults> {

        return movieDbApi.getTrendingMovies(api_key, page_no)
    }
}