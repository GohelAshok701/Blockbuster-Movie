package com.app.roomdatabaseretofithilt.repository

import com.app.roomdatabaseretofithilt.network.ApiService
import com.app.roomdatabaseretofithilt.other.WebUtility
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovieList() = apiService.getMovieLists(WebUtility.API_KEY, WebUtility.LANGUAGE)
}