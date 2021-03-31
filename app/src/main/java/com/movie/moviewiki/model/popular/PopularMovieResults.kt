package com.movie.moviewiki.model.popular

import com.google.gson.annotations.SerializedName

data class PopularMovieResults(
    @SerializedName("results")
    val popularMoviesList: List<PopularMovie>
)
