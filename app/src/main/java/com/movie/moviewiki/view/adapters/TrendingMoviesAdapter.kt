package com.movie.moviewiki.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.movie.moviewiki.R
import com.movie.moviewiki.model.TrendingMovie

class TrendingMoviesAdapter(
    private val context: Context,
    private val trendingMoviesList: List<TrendingMovie>,
): RecyclerView.Adapter<TrendingMoviesAdapter.TrendingMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingMoviesViewHolder {

        val view: View = LayoutInflater.from(context).inflate(
            R.layout.layout_trending_movies,
            parent,
            false
        )
        return TrendingMoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingMoviesViewHolder, position: Int) {

        val url = "https://image.tmdb.org/t/p/original" + trendingMoviesList[position].posterUrl
        Glide.with(context).load(url).into(holder.trendingMoviesImage)
    }

    override fun getItemCount(): Int {

        return trendingMoviesList.size
    }

    class TrendingMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val trendingMoviesCardView: CardView = itemView.findViewById(R.id.trendingMoviesCardView)
        val trendingMoviesImage: ImageView = itemView.findViewById(R.id.trendingMoviesImage)

    }
}