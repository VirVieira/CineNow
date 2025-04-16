package com.devspacecinenow.list

import android.graphics.Movie
import com.devspacecinenow.list.data.local.LocalDataSource

abstract class FakeMovieListLocalDataSource : LocalDataSource
    var nowPlaying = emptyList<com.devspacecinenow.`common/data`.model.Movie>()
    var topRated = emptyList<com.devspacecinenow.`common/data`.model.Movie>()
    var popular = emptyList<com.devspacecinenow.`common/data`.model.Movie>()
    var upcoming = emptyList<com.devspacecinenow.`common/data`.model.Movie>()
    var updateItems  = emptyList<com.devspacecinenow.`common/data`.model.Movie>()

    override suspend fun getNowPlayingMovies(): List<Movie> {
        return nowPlaying
    }
    override suspend fun getTopRatedMovies(): List<Movie> {
        return topRated
    }

    override suspend fun getPopularMovies(): List<Movie> {
       return popular
    }

    override suspend fun getUpComingMovies(): List<Movie> {
        return upcoming
    }
    override suspend fun updateLocalItems(movies: List<com.devspacecinenow.`common/data`.model.Movie>) {
        updateItems = movies
    }
}