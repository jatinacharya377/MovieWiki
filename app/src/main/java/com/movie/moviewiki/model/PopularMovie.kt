package com.movie.moviewiki.model

import com.google.gson.annotations.SerializedName

data class PopularMovie(
    @SerializedName("backdrop_path")
    val backdropUrl: String?
)
