package com.movie.moviewiki.api

import com.movie.moviewiki.di.DaggerApiComponent
import javax.inject.Inject

class MoviesService {

    @Inject
    lateinit var api: MovieDbApi

    init {
        DaggerApiComponent.create().inject(this)
    }
}