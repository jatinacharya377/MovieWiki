package com.movie.moviewiki.model

import com.google.gson.annotations.SerializedName

data class TrendingMovie(
    @SerializedName("poster_path")
    val posterUrl: String?
)
