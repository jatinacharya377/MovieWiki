package com.movie.moviewiki.di

import com.movie.moviewiki.api.TMDBService
import com.movie.moviewiki.repository.TMDBRepository
import com.movie.moviewiki.viewmodel.TMDBViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: TMDBService)
    fun inject(repository: TMDBRepository)
    fun inject(viewModel: TMDBViewModel)
}