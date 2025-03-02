package com.devspacecinenow.detail.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devspacecinenow.common.remote.model.MovieDto
import com.devspacecinenow.detail.data.DetailService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
    class MovieDetailViewModel @Inject constructor(
        private val detalService: DetailService,
    ) : ViewModel() {
        private val _uiMovie = MutableStateFlow<MovieDto?>(null)
        val uiMovie: StateFlow<MovieDto?> = _uiMovie

        fun fetchMovieDetail(movieId: String) {
            if (_uiMovie.value == null) {
                viewModelScope.launch(Dispatchers.IO) {
                    val response = DetailService.getMovieById(movieId)
                    if (response.isSuccessful) {
                        _uiMovie.value = response.body()
                    } else {
                        Log.d(
                            "MovieDetailViewModel",
                            "Request Error :: ${response.erroBody()}"
                        )
                    }
                }
            }
        }
    }
}