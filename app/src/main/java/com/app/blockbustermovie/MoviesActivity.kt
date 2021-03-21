package com.app.blockbustermovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.app.blockbustermovie.databinding.ActivityMoviesBinding
import com.app.roomdatabaseretofithilt.adapter.MovieAdapter
import com.app.roomdatabaseretofithilt.utility.Status
import com.app.roomdatabaseretofithilt.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMoviesBinding
    private val movieViewModel: MovieViewModel by viewModels()
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies)

        binding.rvMovieList.layoutManager = GridLayoutManager(this, 2)

        binding.txtAToZ.setOnClickListener(this)
        binding.txtZToA.setOnClickListener(this)
        binding.txtDateAsc.setOnClickListener(this)
        binding.txtDateDesc.setOnClickListener(this)
        binding.txtRatingAsc.setOnClickListener(this)
        binding.txtRatingDesc.setOnClickListener(this)

        callMovieAPI()
    }

    fun callMovieAPI() {
        movieViewModel.getMovie().observe(this, androidx.lifecycle.Observer {
            it.let { resource ->
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.progress.visibility = GONE
                        binding.llSort.visibility = VISIBLE

                        resource.data?.items?.let { it1 ->
                            movieViewModel.getMovieDao().delete()
                            movieViewModel.getMovieDao().insertAll(
                                it1
                            )

                            movieAdapter = MovieAdapter(this)
                            binding.rvMovieList.adapter = movieAdapter
                            movieAdapter.updateMovieList(
                                movieViewModel.getMovieDao().getAllMovieList()
                            )

                            setFilter(1)

                        }
                    }
                    Status.LOADING -> {
                        binding.progress.visibility = VISIBLE
                    }
                    Status.ERROR -> {
                        binding.progress.visibility = GONE
                    }
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.txtAToZ -> setFilter(1)

            R.id.txtZToA -> setFilter(2)

            R.id.txtDateAsc -> setFilter(3)

            R.id.txtDateDesc -> setFilter(4)

            R.id.txtRatingAsc -> setFilter(5)

            R.id.txtRatingDesc -> setFilter(6)
        }
    }

    private fun setFilter(filterType: Int) {

        binding.txtAToZ.setTextAppearance(this, R.style.textViewStyle)
        binding.txtZToA.setTextAppearance(this, R.style.textViewStyle)
        binding.txtDateAsc.setTextAppearance(this, R.style.textViewStyle)
        binding.txtDateDesc.setTextAppearance(this, R.style.textViewStyle)
        binding.txtRatingAsc.setTextAppearance(this, R.style.textViewStyle)
        binding.txtRatingDesc.setTextAppearance(this, R.style.textViewStyle)

        if (filterType == 1) {
            binding.txtAToZ.setTextAppearance(this, R.style.selectedTextViewStyle)
            movieAdapter.updateMovieList(
                movieViewModel.getMovieDao().getAtoZMovieList()
            )
        } else if (filterType == 2) {
            binding.txtZToA.setTextAppearance(this, R.style.selectedTextViewStyle)
            movieAdapter.updateMovieList(
                movieViewModel.getMovieDao().getZtoAMovieList()
            )
        } else if (filterType == 3) {
            binding.txtDateAsc.setTextAppearance(this, R.style.selectedTextViewStyle)
            movieAdapter.updateMovieList(
                movieViewModel.getMovieDao().getDateAscMovieList()
            )
        } else if (filterType == 4) {
            binding.txtDateDesc.setTextAppearance(this, R.style.selectedTextViewStyle)
            movieAdapter.updateMovieList(
                movieViewModel.getMovieDao().getDateDescMovieList()
            )
        } else if (filterType == 5) {
            binding.txtRatingAsc.setTextAppearance(this, R.style.selectedTextViewStyle)
            movieAdapter.updateMovieList(
                movieViewModel.getMovieDao().getAverageVoteAscMovieList()
            )
        } else {
            binding.txtRatingDesc.setTextAppearance(this, R.style.selectedTextViewStyle)
            movieAdapter.updateMovieList(
                movieViewModel.getMovieDao().getAverageVoteDescMovieList()
            )
        }
    }
}