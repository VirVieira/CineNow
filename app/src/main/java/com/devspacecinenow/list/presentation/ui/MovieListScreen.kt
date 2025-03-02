package com.devspacecinenow.list.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.devspacecinenow.common.remote.model.MovieDto
import com.devspacecinenow.list.presentation.MovieListViewModel


@Composable
fun MovieListScreen(
    navController: NavHostController,
    viewModel: MovieListViewModel = hiltViewModel(),
) {

    val nowPlayingMovies by viewModel.uiNowPlaying.collectAsState()
    val topRatedMovies by viewModel.uiTopRated.collectAsState()
    val popularMovies by viewModel.uiPopular.collectAsState()
    val upcomingMovies by viewModel.uiUpComing.collectAsState()


    MovieListContent(
        topRatedMovies = topRatedMovies,
        nowPlayingMovies = nowPlayingMovies,
        popularMovies = popularMovies,
        upcomingMovies = upcomingMovies
    ) { itemClicked ->
        navController.navigate(route = "movieDetail/ ${itemClicked.id}")
    }
}

@Composable
private fun MovieListContent(
    topRatedMovies: MovieListUiState,
    nowPlayingMovies: MovieListUiState,
    popularMovies: MovieListUiState,
    upcomingMovies: MovieListUiState,
    onClick: (MovieDto) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            text = "CineNow",
        )

        MovieSession(
            label = "Top rated",
            movieListUiState = topRatedMovies,
            onClick = onClick
        )

        MovieSession(
            label = "Now Playing",
            movieListUiState = nowPlayingMovies,
            onClick = onClick
        )

        MovieSession(
            label = "Popular",
            movieListUiState = popularMovies,
            onClick = onClick
        )

        MovieSession(
            label = "Upcoming",
            movieListUiState = upcomingMovies,
            onClick = onClick
        )
    }
}


@Composable
private fun MovieSession(
    label: String,
    movieListUiState: MovieListUiState,
    onClick: (MovieDto) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            fontSize = 24.sp,
            text = label,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(8.dp))
        if(movieListUiState.isLoading) {

        } else if (movieListUiState.isError) {
            Text(
                color = Color.Red,
                text = movieListUiState.errorMessage ?: "",
            )
        } else {
            MovieList(movieList = movieListUiState.list, onClick = onClick)
        }
    }
}

@Composable
private fun MovieList(
    movieList: List<MovieUiData>,
    onClick: (MovieDto) -> Unit
) {
    LazyRow {
        items(movieList) {
            MovieItem(
                movieDto = it,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun MovieItem(
    movieDto: MovieUiData,
    onClick: (MovieDto) -> Unit
) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .clickable {
                onClick.invoke(MovieDto)
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(end = 4.dp)
                .width(120.dp)
                .height(150.dp),
            contentScale = ContentScale.Crop,
            model = movieDto.image,
            contentDescription = "${movieDto.title} Poster image"
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
            text = movieDto.title
        )
        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = movieDto.overview
        )
    }
}