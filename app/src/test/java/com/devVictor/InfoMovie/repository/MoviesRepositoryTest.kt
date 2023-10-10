package com.devVictor.InfoMovie.repository

import com.devVictor.InfoMovie.Movie
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verifyBlocking

class MoviesRepositoryTest {

    @Test
    fun when_Db_isEmpty_server_is_called() {
        val localDataSource = mock<LocalDataSource>() { onBlocking { countMovie() } doReturn 0 }
        val remoteDataSource = mock<RemoteDataSource>()
        val repository = MoviesRepository(localDataSource, remoteDataSource)

        runBlocking { repository.requestMovies() }

        verifyBlocking(remoteDataSource) { getMovies() }
    }

    @Test
    fun when_Db_isEmpty_movies_are_saved_into_DB() {
        val expectedMovie = listOf(Movie(id = 1, title = "Title", overview = "Overview", posterPath = "Poster", favorite = false))
        val localDataSource = mock<LocalDataSource>() { onBlocking { countMovie() } doReturn 0 }
        val remoteDataSource = mock<RemoteDataSource>() { onBlocking { getMovies() } doReturn expectedMovie }
        val repository = MoviesRepository(localDataSource, remoteDataSource)

        runBlocking { repository.requestMovies() }

        verifyBlocking(localDataSource) { insertMovie(expectedMovie) }
    }

    @Test
    fun when_Db_isNotEmpty_remote_Db_is_not_called() {
        val localDataSource = mock<LocalDataSource>() { onBlocking { countMovie() } doReturn 1 }
        val remoteDataSource = mock<RemoteDataSource>()
        val repository = MoviesRepository(localDataSource, remoteDataSource)

        runBlocking { repository.requestMovies() }

        verifyBlocking(remoteDataSource, times(0)) { getMovies() }
    }

    @Test
    fun when_Db_isNotEmpty_movies_are_recovered_from_DB() {
        val localMovie = listOf(Movie(id = 1, title = "Title", overview = "Overview", posterPath = "Poster", favorite = false))
        val remoteMovie = listOf(Movie(id = 2, title = "Title2", overview = "Overview2", posterPath = "Poster2", favorite = false))
        val localDataSource = mock<LocalDataSource>() {
            onBlocking { countMovie() } doReturn 0
            onBlocking { movies } doReturn flowOf(localMovie)
        }
        val remoteDataSource = mock<RemoteDataSource>() {
            onBlocking { getMovies() } doReturn remoteMovie
        }
        val repository = MoviesRepository(localDataSource, remoteDataSource)

        val result = runBlocking {
            repository.requestMovies()
            repository.movies.first()
        }

        assertEquals(localMovie, result)
    }
}