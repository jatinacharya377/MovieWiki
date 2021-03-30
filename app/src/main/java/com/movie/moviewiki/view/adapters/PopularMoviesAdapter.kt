package com.movie.moviewiki.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.movie.moviewiki.R
import com.movie.moviewiki.model.PopularMovie

class PopularMoviesAdapter(
    private val context: Context,
    private val popularMoviesList: List<PopularMovie>
) : RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {

        val view: View =
            LayoutInflater.from(context).inflate(R.layout.layout_popular_movies, parent, false)
        return PopularMoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {

        val url = "https://image.tmdb.org/t/p/original" + popularMoviesList[position].backdropUrl
        Glide.with(context).load(url).into(holder.popularMoviesImage)
    }

    override fun getItemCount(): Int {

        return 4
    }

    class PopularMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val popularMoviesImage: ImageView = itemView.findViewById(R.id.popularMoviesImage)
    }
}