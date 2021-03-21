package com.app.roomdatabaseretofithilt.network

import com.app.roomdatabaseretofithilt.other.WebUtility
import com.app.roomdatabaseretofithilt.responses.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(WebUtility.GET_MOVIE_LIST)
    suspend fun getMovieLists(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): MoviesResponse
}