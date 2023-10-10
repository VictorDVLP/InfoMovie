package com.devVictor.InfoMovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room.databaseBuilder
import com.devVictor.InfoMovie.data.local.InfoMovieDatabase
import com.devVictor.InfoMovie.repository.LocalDataSource
import com.devVictor.InfoMovie.repository.MoviesRepository
import com.devVictor.InfoMovie.repository.RemoteDataSource
import com.devVictor.InfoMovie.ui.screens.ScreenMovies

class MainActivity : ComponentActivity() {
    private lateinit var db: InfoMovieDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = databaseBuilder(applicationContext, InfoMovieDatabase::class.java, "movies-db")
            .build()

        val repository = MoviesRepository(
            localDataSource = LocalDataSource(db.infoMovieDao()),
            remoteDataSource = RemoteDataSource()
        )
        setContent {
            ScreenMovies(repository = repository)
        }
    }
}
