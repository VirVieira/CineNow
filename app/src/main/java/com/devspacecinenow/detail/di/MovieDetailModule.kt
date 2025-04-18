package com.devspacecinenow.detail.di

import com.devspacecinenow.detail.data.DetailService
import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn (ViewModelComponent::class)
class MovieDetailModule {

    @Provides
    fun provideMovieDetailService(retrofit: Retrofit): DetailService {
        return retrofit.create(DetailService::class.java)
    }
}