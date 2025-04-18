package com.devspacecinenow

import android.app.Application
import androidx.room.Room
import com.devspacecinenow.common.data.CineNowDataBase
import com.devspacecinenow.common.local.MovieDao
import com.devspacecinenow.list.data.MovieListRepository
import com.devspacecinenow.list.data.local.LocalDataSource
import com.devspacecinenow.list.data.local.MovieListLocalDataSource
import com.devspacecinenow.list.data.remote.ListService
import com.devspacecinenow.list.data.remote.MovieListRemoteDataSource
import com.devspacecinenow.list.data.remote.RemoteDataSource
import com.devspacecinenow.remote.RetrofitClient

object CineNowServiceLocator {

    fun getRepositroy(application: Application): MovieListRepository {
        val db = Room.databaseBuilder(
            application.applicationContext,
            CineNowDataBase::class.java, "database-cine-now"
        ).build()
        val listService = RetrofitClient.retrofitInstance.create(ListService::class.java)
        val localDataSource: LocalDataSource = MovieListLocalDataSource(db.getMovieDao())
        val remoteDataSource: RemoteDataSource = MovieListRemoteDataSource(listService)

        return MovieListRepository(
            local = localDataSource,
            remote = remoteDataSource
        )
    }

    private fun MovieListLocalDataSource(dao: MovieDao): MovieListLocalDataSource<Any?> {
        TODO("Not yet implemented")
    }
}