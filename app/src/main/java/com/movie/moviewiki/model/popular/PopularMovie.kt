package com.movie.moviewiki.model.popular

import com.google.gson.annotations.SerializedName

data class PopularMovie(
    @SerializedName("backdrop_path")
    val backdropUrl: String?
)
