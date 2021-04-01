package com.movie.moviewiki.di

import com.movie.moviewiki.api.TMDBApi
import com.movie.moviewiki.api.TMDBService
import com.movie.moviewiki.repository.TMDBRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val baseUrl = "https://api.themoviedb.org/3/"

    @Provides
    fun getTMDBApi(): TMDBApi {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TMDBApi::class.java)
    }

    @Provides
    fun getTMDBService(): TMDBService {

        return TMDBService()
    }

    @Provides
    fun getTMDBRepository(): TMDBRepository {

        return TMDBRepository()
    }
}