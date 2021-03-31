package com.movie.moviewiki.utils

import android.content.Context
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.movie.moviewiki.R

@RequiresApi(Build.VERSION_CODES.M)
fun getProgressDrawable(context: Context): CircularProgressDrawable {

    return CircularProgressDrawable(context).apply {
        centerRadius = 50f
        strokeWidth = 10f
        setColorSchemeColors(context.getColor(R.color.teal_200))
        start()
    }
}

fun ImageView.loadImage(posterUrl: String?, progressDrawable: CircularProgressDrawable) {

    val url = "https://image.tmdb.org/t/p/w500" + posterUrl
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)
    Glide.with(this)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}