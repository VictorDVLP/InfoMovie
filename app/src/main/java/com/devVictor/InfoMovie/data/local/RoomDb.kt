package com.devVictor.InfoMovie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalMovie::class], version = 1)
abstract class InfoMovieDatabase : RoomDatabase() {
    abstract fun infoMovieDao(): InfoMovieDao
}