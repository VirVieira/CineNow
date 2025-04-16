package com.devspacecinenow.common.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devspacecinenow.common.local.MovieDao
import com.devspacecinenow.common.local.MovieEntity

@Database([MovieEntity::class], version = 1)
abstract class CineNowDataBase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}