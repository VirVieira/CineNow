package com.devspacecinenow

import android.app.Application
import androidx.room.Room
import com.devspacecinenow.common.data.CineNowDataBase
import com.devspacecinenow.di.CineNowModule
import com.devspacecinenow.list.data.MovieListRepository
import com.devspacecinenow.list.data.local.LocalDataSource
import com.devspacecinenow.list.data.local.MovieListLocalDataSource
import com.devspacecinenow.list.data.remote.ListService
import com.devspacecinenow.list.data.remote.MovieListRemoteDataSource

object CineNowServiceLocator {
    fun getRepository(application: Application): MovieListRepository {
        val db = Room.databaseBuilder(
            application.applicationContext,
            CineNowDataBase::class.java, "database-cine-now"
        ).build()

        val listService = CineNowModule.RetrofitClient.retrofitInstance.create(ListService::class.java)
        val localDataSource: LocalDataSource = MovieListLocalDataSource(db.getMovieDao())
        val remoteDataSource: MovieListRemoteDataSource = MovieListRemoteDataSource(listService)

        return MovieListRepository(
            local = localDataSource,
            remote = remoteDataSource
        )
    }
}