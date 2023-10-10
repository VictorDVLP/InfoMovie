package com.devVictor.InfoMovie.viewModel

import com.devVictor.InfoMovie.Movie

data class UiState(
    var movies: List<Movie> = emptyList(),
    var isLoading: Boolean = false
)