package com.devspacecinenow.common.remote.model

import com.google.gson.annotations.SerializedName


@kotlinx.serialization.Serializable
data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializableName("poster_path")
    val postPath: String,
) {
    val posterFullPath: String
        get() = "https://image.tmdb.org/t/p/w300$postPath"
}

annotation class SerializableName(val value: String)
data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("poster_path")
    val postPath: String
) {
    val posterFullPath: String
        get() = "https://image.tmdb.org/t/p/w300$postPath"
}