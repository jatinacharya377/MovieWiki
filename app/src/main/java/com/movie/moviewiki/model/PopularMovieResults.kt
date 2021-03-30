package com.movie.moviewiki.model

import com.google.gson.annotations.SerializedName

data class PopularMovieResults(
    @SerializedName("results")
    val popularMoviesList: List<PopularMovie>
)
