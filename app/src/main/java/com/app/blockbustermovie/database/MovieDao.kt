package com.app.roomdatabaseretofithilt.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.blockbustermovie.responses.Movie
import com.app.roomdatabaseretofithilt.responses.MoviesResponse

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie")
    fun getAllMovieList(): List<Movie>

    @Insert
    fun insertAll(movies: List<Movie>)

    @Query("DELETE FROM Movie")
    fun delete()

    //filter queries
    @Query("select * from Movie ORDER By Movie.title asc")
    fun getAtoZMovieList(): List<Movie>

    @Query("select * from Movie ORDER By Movie.title desc")
    fun getZtoAMovieList(): List<Movie>

    @Query("select * from Movie ORDER By Movie.release_date asc")
    fun getDateAscMovieList(): List<Movie>

    @Query("select * from Movie ORDER By Movie.release_date desc")
    fun getDateDescMovieList(): List<Movie>

    @Query("select * from Movie ORDER By Movie.vote_average asc")
    fun getAverageVoteAscMovieList(): List<Movie>

    @Query("select * from Movie ORDER By Movie.vote_average desc")
    fun getAverageVoteDescMovieList(): List<Movie>

}