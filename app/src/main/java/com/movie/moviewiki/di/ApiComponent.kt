package com.movie.moviewiki.di

import com.movie.moviewiki.api.MoviesService
import com.movie.moviewiki.repository.MovieRepository
import com.movie.moviewiki.viewmodel.MovieViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: MoviesService)
    fun inject(repository: MovieRepository)
    fun inject(viewModel: MovieViewModel)
}