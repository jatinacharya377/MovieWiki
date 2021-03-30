package com.movie.moviewiki.model

import com.google.gson.annotations.SerializedName

data class TrendingMovieResults(
    @SerializedName("results")
    val trendingMoviesList: List<TrendingMovie>
)
