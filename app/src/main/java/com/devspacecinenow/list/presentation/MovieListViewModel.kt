package com.devspacecinenow.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.devspacecinenow.CineNowApplication
import com.devspacecinenow.di.CineNowModule
import com.devspacecinenow.list.data.MovieListRepository
import com.devspacecinenow.list.data.remote.ListService
import com.devspacecinenow.list.presentation.ui.MovieListUiState
import com.devspacecinenow.list.presentation.ui.MovieUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieListRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val _uiNowPlaying = MutableStateFlow(MovieListUiState())
    val uiNowPlaying: StateFlow<MovieListUiState> = _uiNowPlaying

    // Top Rated
    private val _uiTopRated = MutableStateFlow(MovieListUiState())
    val uiTopRated: StateFlow<MovieListUiState> = _uiTopRated

    // Popular
    private val _uiPopular = MutableStateFlow(MovieListUiState())
    val uiPopular: StateFlow<MovieListUiState> = _uiPopular

    //UpComing
    private val _uiUpComing = MutableStateFlow(MovieListUiState())
    val uiUpComing: StateFlow<MovieListUiState> = _uiUpComing

    init {
        fetchNowPlayingMovies()
        fetchTopRatedMovies()
        fetchPopularMovies()
        fetchUpComingMovies()
    }

    private fun fetchNowPlayingMovies() {
        _uiNowPlaying.value = MovieListUiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getNowPlaying()
            if (result.isSuccess) {
                val movies = result.getOrNull()
                if (movies != null) {
                    val movieUiDataList = movies.map { movieDto ->
                        MovieUiData(
                            id = movieDto.id,
                            title = movieDto.title,
                            overview = movieDto.overview,
                            image = movieDto.image
                        )
                    }
                    _uiNowPlaying.value = MovieListUiState(list = movieUiDataList)
                }
            } else {
                val ex = result.exceptionOrNull()
                if (ex is UnknownHostException) {
                    _uiNowPlaying.value = MovieListUiState(
                        isError = true,
                        errorMessage = "Not internet connection"
                    )
                } else {
                    _uiNowPlaying.value = MovieListUiState(isError = true)
                }
            }
        }
    }

    private fun fetchUpComingMovies() {
        _uiUpComing.value = MovieListUiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getUpcoming()
                if (response.isSuccess) {
                    val movies = response.getOrNull()
                    if (movies != null) {
                        val movieUiDataList = movies.map { movieDto ->
                            MovieUiData(
                                id = movieDto.id,
                                title = movieDto.title,
                                overview = movieDto.overview,
                                image = movieDto.image
                            )
                        }
                        _uiUpComing.value = MovieListUiState(list = movieUiDataList)
                    }
                } else {
                    _uiUpComing.value = MovieListUiState(isError = true)
                }
            } finally {

            }
        }
    }


    private fun fetchPopularMovies() {
        _uiPopular.value = MovieListUiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getPopular()
                if (response.isSuccess) {
                    val movies = response.getOrNull()
                    if (movies != null) {
                        val movieUiDataList = movies.map { movieDto ->
                            MovieUiData(
                                id = movieDto.id,
                                title = movieDto.title,
                                overview = movieDto.overview,
                                image = movieDto.image
                            )
                        }
                        _uiPopular.value = MovieListUiState(list = movieUiDataList)
                    }
                } else {
                    _uiPopular.value = MovieListUiState(isError = true)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                if (ex is UnknownHostException) {
                    _uiPopular.value = MovieListUiState(
                        isError = true,
                        errorMessage = "Not internet connection"
                    )
                } else {
                    _uiPopular.value = MovieListUiState(isError = true)
                }
            }
        }
    }


    private fun fetchTopRatedMovies() {
        _uiTopRated.value = MovieListUiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            ->
            try {
                val response = repository.getTopRated()
                if (response.isSuccess) {
                    val movies = response.getOrNull()
                    if (movies != null) {
                        val movieUiDataList = movies.map { movieDto ->
                            MovieUiData(
                                id = movieDto.id,
                                title = movieDto.title,
                                overview = movieDto.overview,
                                image = movieDto.image
                            )
                        }
                        _uiTopRated.value = MovieListUiState(list = movieUiDataList)
                    }
                } else {
                    val ex = response.exceptionOrNull()
                    if (ex is UnknownHostException) {
                        _uiTopRated.value = MovieListUiState(
                            isError = true,
                            errorMessage = "Not Internet connection"
                        )
                    } else {
                        _uiTopRated.value = MovieListUiState(isError = true)
                    }
                }
            } finally {

            }
        }
    }
}
