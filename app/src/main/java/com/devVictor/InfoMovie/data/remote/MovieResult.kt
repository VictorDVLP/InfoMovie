package com.devVictor.InfoMovie.data.remote

import com.devVictor.InfoMovie.data.remote.InfoMovies

data class MovieResult (
    val page: Int,
    val results: List<InfoMovies>,
    val total_pages: Int,
    val total_results: Int
)
