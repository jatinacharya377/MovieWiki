package com.movie.moviewiki.view.activities

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.movie.moviewiki.R
import com.movie.moviewiki.utils.HorizontalMarginItemDecoration
import com.movie.moviewiki.view.adapters.PopularMoviesAdapter
import com.movie.moviewiki.view.adapters.TrendingMoviesAdapter
import com.movie.moviewiki.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private var page_no: Int = 1
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var loadingProgressBar: ProgressBar
    private lateinit var trendingMoviesRecyclerView: RecyclerView
    private lateinit var trendingMoviesAdapter: TrendingMoviesAdapter
    private lateinit var popularMoviesViewPager: ViewPager2

    private fun initializeViews() {

        loadingProgressBar = findViewById(R.id.loadingProgressBar)
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.init(getString(R.string.api_key))
        popularMoviesViewPager = findViewById(R.id.popularMoviesViewPager)
        trendingMoviesRecyclerView = findViewById(R.id.trendingMoviesRecyclerView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        movieViewModel.getPopularMovies().observe(this, { popularMoviesList ->

            popularMoviesAdapter = PopularMoviesAdapter(this@MainActivity, popularMoviesList)
            loadingProgressBar.visibility = View.GONE
            popularMoviesViewPager.adapter = popularMoviesAdapter
            popularMoviesViewPager.offscreenPageLimit = 1
            val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
            val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
            val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
            val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->

                page.translationX = -pageTranslationX * position
                page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
            }
            popularMoviesViewPager.setPageTransformer(pageTransformer)
            val itemDecoration = HorizontalMarginItemDecoration(this, R.dimen.viewpager_current_item_horizontal_margin)
            popularMoviesViewPager.addItemDecoration(itemDecoration)
            popularMoviesViewPager.setCurrentItem(1, true)
        })
        movieViewModel.getTrendingMovies(page_no).observe(this, { trendingMoviesList ->

            trendingMoviesAdapter = TrendingMoviesAdapter(this, trendingMoviesList)
            trendingMoviesRecyclerView.apply {
                layoutManager = GridLayoutManager(this@MainActivity, 3)
                setHasFixedSize(true)
                adapter = trendingMoviesAdapter
            }
        })
    }
}