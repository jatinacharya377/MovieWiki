package com.movie.moviewiki.api

import com.movie.moviewiki.di.DaggerApiComponent
import javax.inject.Inject

class TMDBService {

    @Inject
    lateinit var api: TMDBApi

    init {
        DaggerApiComponent.create().inject(this)
    }
}