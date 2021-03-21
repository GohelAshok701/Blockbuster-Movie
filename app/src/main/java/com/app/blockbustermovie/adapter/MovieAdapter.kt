package com.app.roomdatabaseretofithilt.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.blockbustermovie.MovieDetailActivity
import com.app.blockbustermovie.R
import com.app.blockbustermovie.responses.Movie
import com.app.blockbustermovie.utility.AppConstatnt
import com.app.roomdatabaseretofithilt.other.WebUtility
import com.bumptech.glide.Glide

class MovieAdapter(private val context: Context) :
    RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    lateinit var movieList: List<Movie>

    class MovieHolder(v: View) : RecyclerView.ViewHolder(v) {
        var ivPoster: ImageView = v.findViewById(R.id.ivPoster)
        var txtMovieName: TextView = v.findViewById(R.id.txtMovieName)
        var txtReleaseDate: TextView = v.findViewById(R.id.txtReleaseDate)
        var txtRating: TextView = v.findViewById(R.id.txtRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.raw_movie, parent, false)
        return MovieHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        val movie: Movie = movieList.get(position)

        Glide.with(context)
            .load(WebUtility.IMAGE_BASE_URL + movie.poster_path)
            .into(holder.ivPoster)

        holder.txtMovieName.text = context.resources.getString(R.string.name, movie.title)
        holder.txtReleaseDate.text =
            context.resources.getString(R.string.movie_release, movie.release_date)
        holder.txtRating.text =
            context.resources.getString(R.string.movie_rating, movie.vote_average)

        holder.itemView.setOnClickListener {
            context.startActivity(
                Intent(
                    context,
                    MovieDetailActivity::class.java
                ).putExtra(AppConstatnt.MOVIE_MODEL, movie)
            )
        }
    }

    fun updateMovieList(movieList: List<Movie>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }
}