package com.devVictor.InfoMovie.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface InfoMovieDao {
    @Query("SELECT * FROM Films")
    fun getMovies(): Flow<List<LocalMovie>>

    @Insert
    suspend fun insertMovie(movie: List<LocalMovie>)

    @Update
    suspend fun updateMovie(movie: LocalMovie)

    @Query("SELECT COUNT(*) FROM Films")
    suspend fun getMovieCount(): Int
}