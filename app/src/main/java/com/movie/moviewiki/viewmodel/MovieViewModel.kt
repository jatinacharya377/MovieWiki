package com.movie.moviewiki.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movie.moviewiki.model.PopularMovie
import com.movie.moviewiki.model.PopularMovieResults
import com.movie.moviewiki.model.TrendingMovie
import com.movie.moviewiki.model.TrendingMovieResults
import com.movie.moviewiki.repository.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class MovieViewModel: ViewModel() {

    private lateinit var movieRepository: MovieRepository
    private val disposable = CompositeDisposable()
    private val popularMoviesList = MutableLiveData<List<PopularMovie>>()
    private val trendingMoviesList = MutableLiveData<List<TrendingMovie>>()

    fun getPopularMovies(): LiveData<List<PopularMovie>> {

        disposable.add(
            movieRepository.getPopularMovies()
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

    fun getTrendingMovies(page_no: Int): LiveData<List<TrendingMovie>> {

        disposable.add(
            movieRepository.getTrendingMovies(page_no)
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

        movieRepository = MovieRepository(api_key)
        movieRepository.createApi()
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}