package com.movie.moviewiki.view.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.movie.moviewiki.R
import com.movie.moviewiki.model.popular.PopularMovie
import com.movie.moviewiki.utils.getProgressDrawable
import com.movie.moviewiki.utils.loadImage

class PopularMoviesAdapter(
    private val context: Context,
    private val popularMoviesList: List<PopularMovie>
) : RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {

        val view: View = LayoutInflater.from(context).inflate(R.layout.layout_popular_movies, parent, false)
        return PopularMoviesViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {

        holder.bind(popularMoviesList[position])
    }

    override fun getItemCount(): Int {

        return 4
    }

    @RequiresApi(Build.VERSION_CODES.M)
    class PopularMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val popularMoviesImage: ImageView = itemView.findViewById(R.id.popularMoviesImage)
        private val progressDrawable = getProgressDrawable(itemView.context)

        fun bind(popularMovie: PopularMovie) {

            val url = "https://image.tmdb.org/t/p/original" + popularMovie.backdropUrl
            popularMoviesImage.loadImage(url, progressDrawable)
        }
    }
}