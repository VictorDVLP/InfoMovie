package com.devVictor.InfoMovie.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object MoviesApi {
    val retrofitService: MoviesService by lazy {
        retrofit.create(MoviesService::class.java)
    }
}