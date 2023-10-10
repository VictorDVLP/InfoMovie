package com.devVictor.InfoMovie.extensions

import com.devVictor.InfoMovie.Movie
import com.devVictor.InfoMovie.data.local.LocalMovie
import com.devVictor.InfoMovie.data.remote.InfoMovies

fun LocalMovie.toMovie() = Movie (
    id = 0,
    title = title,
    overview = overview,
    posterPath = poster_path,
    favorite = favorite
    )

fun Movie.toLocalMovie() = LocalMovie (
    id = id,
    title = title,
    overview = overview,
    poster_path = posterPath,
    favorite = favorite
    )