package com.devspacecinenow.common.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.google.android.engage.video.datamodel.MovieEntity


@Dao
interface MovieDao {

    @Query("Select * From movieentity Where category = :category")
    suspend fun getMoviesByCategory(category: String): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)
}