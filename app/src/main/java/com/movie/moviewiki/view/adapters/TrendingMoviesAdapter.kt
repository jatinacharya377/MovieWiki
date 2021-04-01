package com.movie.moviewiki.view.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.movie.moviewiki.R
import com.movie.moviewiki.model.trending.TrendingMovie
import com.movie.moviewiki.utils.getProgressDrawable
import com.movie.moviewiki.utils.loadImage

class TrendingMoviesAdapter(
    private val context: Context,
    private var trendingMoviesList: ArrayList<TrendingMovie>
): RecyclerView.Adapter<TrendingMoviesAdapter.TrendingMoviesViewHolder>() {

    private lateinit var trendingMovieListener: TrendingMovieListener

    interface TrendingMovieListener {

        fun onMovieClick(id: String)
    }

    fun setTrendingMovieListener(trendingMovieListener: TrendingMovieListener) {

        this.trendingMovieListener = trendingMovieListener
    }

    fun updateDataSet(trendingMoviesList: List<TrendingMovie>) {

        this.trendingMoviesList.addAll(trendingMoviesList as ArrayList<TrendingMovie>)
        notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingMoviesViewHolder {

        val view: View = LayoutInflater.from(context).inflate(
            R.layout.layout_trending_movies,
            parent,
            false
        )
        return TrendingMoviesViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: TrendingMoviesViewHolder, position: Int) {

        holder.bind(trendingMoviesList[position])
        holder.trendingMoviesCardView.setOnClickListener {

            trendingMoviesList[position].id?.let { it1 -> trendingMovieListener.onMovieClick(it1) }
        }
    }

    override fun getItemCount(): Int {

        return trendingMoviesList.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    class TrendingMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val progressDrawable = getProgressDrawable(itemView.context)
        private val trendingMoviesImage: ImageView = itemView.findViewById(R.id.trendingMoviesImage)
        val trendingMoviesCardView: CardView = itemView.findViewById(R.id.trendingMoviesCardView)

        fun bind(trendingMovie: TrendingMovie) {

            val url = "https://image.tmdb.org/t/p/w500" + trendingMovie.posterUrl
            trendingMoviesImage.loadImage(url, progressDrawable)
        }
    }
}