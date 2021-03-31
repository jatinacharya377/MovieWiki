package com.movie.moviewiki.model.trending

import com.google.gson.annotations.SerializedName

data class TrendingMovie(
    @SerializedName("id")
    val id: String?,
    @SerializedName("poster_path")
    val posterUrl: String?
)
