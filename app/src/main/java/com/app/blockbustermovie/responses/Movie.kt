package com.app.blockbustermovie.responses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id:Int,

    @ColumnInfo
    val backdrop_path: String?,

    @ColumnInfo
    val poster_path: String?,

    @ColumnInfo
    val overview: String?,

    @ColumnInfo
    val popularity: Double?,

    @ColumnInfo
    val release_date: String?,

    @ColumnInfo
    val title: String?,

    @ColumnInfo
    val vote_average: String?,

    @ColumnInfo
    val vote_count: String?
)