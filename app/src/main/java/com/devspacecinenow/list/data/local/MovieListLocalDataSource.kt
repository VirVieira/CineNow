package com.devspacecinenow.list.data.local

import com.devspacecinenow.common.local.MovieCategory
import com.devspacecinenow.common.local.MovieDao
import com.devspacecinenow.common.local.MovieEntity
import com.devspacecinenow.common.local.model.Movie


class MovieListLocalDataSource<T>(
    private val dao: MovieDao,
    override val it: Any
) : LocalDataSource {

    override suspend fun getNowPlayingMovies(): List<Movie> {
        return getMoviesByCategory(MovieCategory.NowPlaying)
    }

    override suspend fun getTopRatedMovies(): List<Movie> {
        return getMoviesByCategory(MovieCategory.TopRated)
    }

    override suspend fun getPopularMovies(): List<Movie> {
        return getMoviesByCategory(MovieCategory.Popular)
    }

    override suspend fun getUpComingMovies(): List<Movie> {
        return getMoviesByCategory(MovieCategory.Upcoming)
    }
    override suspend fun updateLocalItems(movies: List<Movie>) {
        val entities = movies.map {
            MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                category = it.category,
                image = it.image
            )
        }
        dao.insertAll(entities)
    }

    private suspend fun getMoviesByCategory(movieCategory: MovieCategory): List<Movie> {
        val entities = dao.getMoviesByCategory(movieCategory.name)
        return entities.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                image = it.image,
                category = it.category
            )
        }
    }
}