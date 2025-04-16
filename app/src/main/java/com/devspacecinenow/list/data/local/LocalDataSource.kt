package com.devspacecinenow.list.data.local

import com.devspacecinenow.common.local.model.Movie

interface LocalDataSource {
    abstract val it: Any

    suspend fun getNowPlayingMovies(): List<Movie>

    suspend fun getTopRatedMovies(): List<Movie>

    suspend fun getPopularMovies(): List<Movie>

    suspend fun getUpComingMovies(): List<Movie>

    suspend fun updateLocalItems(movies: List<Movie>)

}