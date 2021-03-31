package com.movie.moviewiki.api

import com.movie.moviewiki.model.MovieDetails
import com.movie.moviewiki.model.popular.PopularMovieResults
import com.movie.moviewiki.model.trending.TrendingMovieResults
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbApi {

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") id: String,
        @Query("api_key") api_key: String
    ): Single<MovieDetails>
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") api_key: String): Single<PopularMovieResults>
    @GET("movie/now_playing")
    fun getTrendingMovies(
        @Query("api_key") api_key: String,
        @Query("page") page_no: Int
    ): Single<TrendingMovieResults>
}