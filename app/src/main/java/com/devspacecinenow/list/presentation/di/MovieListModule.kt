package com.devspacecinenow.list.presentation.di

import com.devspacecinenow.list.data.remote.ListService
import com.devspacecinenow.list.data.local.LocalDataSource
import com.devspacecinenow.list.data.local.MovieListLocalDataSource
import com.devspacecinenow.list.data.remote.MovieListRemoteDataSource
import com.devspacecinenow.list.data.remote.RemoteDataSource
import com.google.android.datatransport.runtime.dagger.Binds
import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit


@Module
@InstallIn(viewModelComponent::class)
class MovieListModule {

    @Provides
    fun providesListService(retrofit: Retrofit): ListService {
        return retrofit.create(ListService::class.java)
    }
}
@Module
@InstallIn(viewModelComponent::class)
interface MovieListModuleBinding {
    @Binds
    fun bindLocalDataSource(impl:MovieListLocalDataSource): LocalDataSource

    @Binds
    fun bindRemoteDataSource(impl: MovieListRemoteDataSource): RemoteDataSource
}