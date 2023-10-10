package com.devVictor.InfoMovie.repository

import com.devVictor.InfoMovie.Movie
import com.devVictor.InfoMovie.data.local.InfoMovieDao
import com.devVictor.InfoMovie.extensions.toLocalMovie
import com.devVictor.InfoMovie.extensions.toMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSource(private val dao: InfoMovieDao) {
    val movies: Flow<List<Movie>> = dao.getMovies().map { movies -> movies.map { it.toMovie() } }

    suspend fun updateMovie(movie: Movie) {
        dao.updateMovie(movie.toLocalMovie())
    }

    suspend fun insertMovie(movie: List<Movie>) {
        dao.insertMovie(movie.map { it.toLocalMovie() })
    }

    suspend fun countMovie(): Int {
        return dao.getMovieCount()
    }
}