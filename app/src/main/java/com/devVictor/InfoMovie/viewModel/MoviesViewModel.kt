package com.devVictor.InfoMovie.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devVictor.InfoMovie.Movie
import com.devVictor.InfoMovie.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            _state.value = UiState(isLoading = true)
            repository.requestMovies()
            repository.movies.collect {
                _state.value =
                    UiState(
                        isLoading = false,
                        movies = it,
                    )
            }
        }
    }

    fun onClickFavorite(movie: Movie) {
        viewModelScope.launch {
            repository.updateMovie(movie.copy(favorite = !movie.favorite))
        }
    }
}