package com.app.roomdatabaseretofithilt.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.blockbustermovie.responses.Movie

@Database(entities = arrayOf(Movie::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}