package com.movie.moviewiki.model

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("name")
    val name: String?
)
