package com.devspacecinenow.di

import android.app.Application
import androidx.room.Room
import com.devspacecinenow.`common/data`.data.RetrofitClient
import com.devspacecinenow.`common/data`.local.CineNowDataBase
import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
class CineNowModule {

    @Provides
    fun providesCineNowDataBase(application: Application): CineNowDataBase {
        return Room.databaseBuilder(
            application.applicationContext,
            CineNowDataBase::class.java, "database-cine-now"
        ).build()
    }

    @Provides
    fun providesRetrofitInstance(): Retrofit {
        return RetrofitClient.retrofitInstance
    }

    @Provides
    @DispatcherIO
    fun providesDispatcherIo(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}