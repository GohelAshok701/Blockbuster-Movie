package com.app.blockbustermovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.app.blockbustermovie.databinding.ActivityMovieDetailBinding
import com.app.blockbustermovie.responses.Movie
import com.app.blockbustermovie.utility.AppConstatnt
import com.app.roomdatabaseretofithilt.other.WebUtility
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        var movieModel = intent.getSerializableExtra(AppConstatnt.MOVIE_MODEL) as Movie

        setMovieDetail(movieModel)

    }


    private fun setMovieDetail(movie: Movie) {
        Glide.with(this)
            .load(WebUtility.IMAGE_BASE_URL + movie.poster_path)
            .into(binding.ivPoster)

        binding.txtMovieName.text =
            resources.getString(R.string.name, movie.title)
        binding.txtReleaseDate.text =
            resources.getString(R.string.movie_release, movie.release_date)
        binding.txtRating.text =
            resources.getString(R.string.movie_rating, movie.vote_average)
        binding.txtOverView.text = movie.overview
    }
}