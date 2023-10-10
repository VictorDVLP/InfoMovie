package com.devVictor.InfoMovie.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Films")
data class LocalMovie(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "posterPath") val poster_path: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean = false
)
