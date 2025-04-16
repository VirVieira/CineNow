package com.devspacecinenow.detail.data.remote.model


@kotlinx.serialization.Serializable
data class MovieResponse(
    val results: List<MovieDto>
)
