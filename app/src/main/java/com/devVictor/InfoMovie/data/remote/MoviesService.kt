package com.devVictor.InfoMovie.data.remote

import com.devVictor.InfoMovie.data.remote.MovieResult
import retrofit2.http.GET

interface MoviesService {
    @GET("discover/movie?api_key=8f5e0ed7227ecc0b7bf2011dc6622baa")
    suspend fun getPopularMovies(): MovieResult
}