package com.devspacecinenow

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("now_playing?language=en-US&page=1")
    fun getNowPlayingMovies() : Call<MovieResponse>

    @GET("top_rated?language=en-US&page=1")
    fun getTopRatedMovies() : Call<MovieResponse>

    @GET("popular?language=en-US&page=1")
    fun getPopularMovies(): Call<MovieResponse>

    @GET("upcoming?language=en-US&page=1")
    fun getUpcomingMovies(): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieById(
        @Path("movie_id") movieId: String,
        @Query("language") language: String = "en-US"
    ): Call<MovieResponse>
}
