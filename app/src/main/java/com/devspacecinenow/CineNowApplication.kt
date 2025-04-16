package com.devspacecinenow

import android.app.Application
import androidx.room.Room
import com.devspacecinenow.common.data.CineNowDataBase
import com.devspacecinenow.common.local.MovieDao
import com.devspacecinenow.di.CineNowModule
import com.devspacecinenow.list.data.MovieListRepository
import com.devspacecinenow.list.data.local.MovieListLocalDataSource
import com.devspacecinenow.list.data.remote.ListService
import com.devspacecinenow.list.data.remote.MovieListRemoteDataSource
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CineNowApplication : Application() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            CineNowDataBase::class.java, "database-cine-now"
        ).build()
    }
    private val listService by lazy {
        CineNowModule.RetrofitClient.retrofitInstance.create(ListService::class.java)
    }
    private val localDataSource: MovieListLocalDataSource by lazy {
        MovieListLocalDataSource(db.getMovieDao())
    }

    private val remoteDataSource: MovieListRemoteDataSource by lazy {
        MovieListRemoteDataSource(listService)
    }
    val repository: MovieListRepository by lazy {
        MovieListRepository(
            local = localDataSource,
            remote = remoteDataSource
        )
    }
}