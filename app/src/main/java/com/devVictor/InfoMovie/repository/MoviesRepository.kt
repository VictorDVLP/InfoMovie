package com.devVictor.InfoMovie.repository

import com.devVictor.InfoMovie.Movie
import kotlinx.coroutines.flow.Flow

class MoviesRepository(
    private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource
) {
    val movies: Flow<List<Movie>> = localDataSource.movies

    suspend fun updateMovie(movie: Movie) {
        localDataSource.updateMovie(movie)
    }

    suspend fun requestMovies() {
        val isDbEmpty = localDataSource.countMovie() == 0
        if (isDbEmpty) {
            localDataSource.insertMovie(remoteDataSource.getMovies())
        }
    }
}
