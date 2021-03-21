package com.app.roomdatabaseretofithilt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.roomdatabaseretofithilt.database.MovieDao
import com.app.roomdatabaseretofithilt.repository.MovieRepository
import com.app.roomdatabaseretofithilt.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieDao: MovieDao
) : ViewModel() {

    fun getMovie() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = movieRepository.getMovieList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getMovieDao(): MovieDao {
        return movieDao
    }
}