package com.devspacecinenow.list.data

import com.devspacecinenow.list.data.local.LocalDataSource
import com.devspacecinenow.list.data.remote.RemoteDataSource
import javax.inject.Inject

class MovieListRepository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
) {
    suspend fun getNowPlaying(): Result<List<Movie>?> {
        return try {
            val result = remote.getNowPlaying()
            if (result.isSuccess) {
                val moviesRemote = result.getOrNull() ?: emptyList()
                if (moviesRemote.isNotEmpty()) {
                    local.updateLocalItems(moviesRemote)
                }
                // Source of Truth
                return Result.success(local.getNowPlayingMovies())
            } else {
                val localData = local.getNowPlayingMovies()
                if (localData.isNotEmpty()) {
                    return result
                } else {
                    Result.success(localData)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        }
    }

    suspend fun getTopRated(): Result<List<Movie>?> {
        return try {
            val result = remote.getTopRated()
            if (result.isSuccess) {
                val moviesRemote = result.getOrNull() ?: emptyList()
                if (moviesRemote.isNotEmpty()) {
                    local.updateLocalItems(moviesRemote)
                }
                // Source of Truth
                return Result.success(local.getTopRatedMovies())
            } else {
                val localData = local.getTopRatedMovies())
                if (localData.isNotEmpty()) {
                    return result
                } else {
                    Result.success(localData)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        }
    }

    suspend fun getPopular(): Result<List<Movie>?> {
        return try {
            val result = remote.getPopular()
            if (result.isSuccess) {
                val moviesRemote = result.getOrNull() ?: emptyList()
                if (moviesRemote.isNotEmpty()) {
                    local.updateLocalItems(moviesRemote)
                }
                // Source of Truth
                return Result.success(local.getPopularMovies())
            } else {
                val localData = local.getPopularMovies()
                if (localData.isEmpty()) {
                    return result
                } else {
                    Result.success(localData)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        }
    }

    suspend fun getUpcoming(): Result<List<Movie>?> {
        return try {
            val result = remote.getUpcoming()
            if (result.isSuccess) {
                val movieRemote = result.getOrNull() ?: emptyList()
                if (movieRemote.isNotEmpty()) {
                    local.updateLocalItems(movieRemote)
                }
                // Source of Truth
                return Result.success(local.getUpComingMovies())
            } else {
                val localData = local.getUpComingMovies()
                if (localData.isEmpty()) {
                    return result
                } else {
                    Result.success(localData)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        }
    }
}