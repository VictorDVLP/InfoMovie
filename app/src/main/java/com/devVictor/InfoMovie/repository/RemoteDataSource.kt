package com.devVictor.InfoMovie.repository

import com.devVictor.InfoMovie.Movie
import com.devVictor.InfoMovie.data.remote.MoviesApi
import com.devVictor.InfoMovie.data.remote.toMovie

class RemoteDataSource {
    suspend fun getMovies(): List<Movie> {
        val listMoviesRemote = MoviesApi.retrofitService.getPopularMovies().results
        return listMoviesRemote.map { it.toMovie() }
    }
}