package com.devVictor.InfoMovie.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.devVictor.InfoMovie.Movie
import com.devVictor.InfoMovie.repository.MoviesRepository
import com.devVictor.InfoMovie.ui.theme.InfoMovieTheme
import com.devVictor.InfoMovie.viewModel.MoviesViewModel

@Composable
fun ScreenMovies(repository: MoviesRepository) {
    val viewModel: MoviesViewModel = viewModel { MoviesViewModel(repository) }
    val state by viewModel.state.collectAsState()
    InfoMovieTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Center
                ) {
                    CircularProgressIndicator()
                }
            }
            if (state.movies.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    LazyVerticalGrid(columns = GridCells.Adaptive(200.dp)) {
                        items(state.movies) { movie ->
                            CardItem(
                                movie = movie,
                                onClick = { viewModel.onClickFavorite(movie = movie) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardItem(movie: Movie, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable(onClick = onClick)) {
        Box {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w185/${movie.posterPath}",
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2 / 3f)
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            )
            if (movie.favorite) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .align(TopEnd)
                        .padding(16.dp),
                    tint = Color.White
                )
            }
        }
        Text(
            text = movie.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp, bottom = 16.dp)
        )
    }
}
