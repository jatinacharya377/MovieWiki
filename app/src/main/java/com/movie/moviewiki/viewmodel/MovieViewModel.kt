package com.movie.moviewiki.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movie.moviewiki.di.DaggerApiComponent
import com.movie.moviewiki.model.*
import com.movie.moviewiki.model.popular.PopularMovie
import com.movie.moviewiki.model.popular.PopularMovieResults
import com.movie.moviewiki.model.trending.TrendingMovie
import com.movie.moviewiki.model.trending.TrendingMovieResults
import com.movie.moviewiki.repository.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MovieViewModel: ViewModel() {

    @Inject
    lateinit var movieRepository: MovieRepository
    private lateinit var trendingMoviesList: MutableLiveData<List<TrendingMovie>>
    private lateinit var apiKey: String
    private val disposable = CompositeDisposable()
    private val movieDetails = MutableLiveData<MovieDetails>()
    private val popularMoviesList = MutableLiveData<List<PopularMovie>>()


    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getMovieDetails(id: String): LiveData<MovieDetails> {

        disposable.add(
            movieRepository.getMovieDetails(id, apiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<MovieDetails>() {

                    override fun onSuccess(value: MovieDetails?) {

                        movieDetails.value = value
                    }

                    override fun onError(e: Throwable?) {

                        e?.message?.let { Log.e("error", it) }
                    }
                })
        )

        return movieDetails
    }

    fun getPopularMovies(): LiveData<List<PopularMovie>> {

        disposable.add(
            movieRepository.getPopularMovies(apiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<PopularMovieResults>() {

                    override fun onSuccess(value: PopularMovieResults?) {

                        popularMoviesList.value = value?.popularMoviesList
                    }

                    override fun onError(e: Throwable?) {

                        e?.message?.let { Log.e("error", it) }
                    }
                })
        )

        return popularMoviesList
    }

    fun getTrendingMovies(pageNo: Int): LiveData<List<TrendingMovie>> {

        trendingMoviesList = MutableLiveData<List<TrendingMovie>>()
        disposable.add(
            movieRepository.getTrendingMovies(apiKey, pageNo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<TrendingMovieResults>() {

                    override fun onSuccess(value: TrendingMovieResults?) {

                        trendingMoviesList.value = value?.trendingMoviesList
                    }

                    override fun onError(e: Throwable?) {

                        e?.message?.let { Log.e("error", it) }
                    }
                })
        )

        return trendingMoviesList
    }

    fun init(api_key: String) {

        this.apiKey = api_key
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}