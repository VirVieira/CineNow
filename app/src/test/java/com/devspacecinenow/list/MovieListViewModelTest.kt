package com.devspacecinenow.list

import com.devspacecinenow.common.`/data`.MovieCategory
import com.devspacecinenow.`common/data`.model.Movie
import com.devspacecinenow.list.data.MovieListRepository
import com.devspacecinenow.list.presentation.MovieListViewModel
import com.devspacecinenow.list.presentation.ui.MovieListUiState
import com.devspacecinenow.list.presentation.ui.MovieUiData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test


class MovieListViewModelTest {
    private val repository: MovieListRepository = mock()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler())

    private val underTest by lazy {
        MovieListViewModel(repository, testDispatcher)
    }

    @Test
    fun `Given fresh viewModel When collecting to topRated Then assert expected value`() {
        runTest {
            // Given
            val movies = listOf<Movie>(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.TopRated.name
                )
            )
            // Valor do Duble
            whenever(repository.getTopRated()).thenReturn(Result.success(movies))
            //When
            val result = underTest.uiTopRated.take(2).toList()[1]

            //Then assert expected value
            val expected = MovieListUiState(
                list = listOf(
                    MovieUiData(
                        id = 1,
                        title = "title1",
                        overview = "overview1",
                        image = "image1"
                    )
                )
            )
            assertEquals(expected, result)
        }
    }

    @Test
    fun `Given fresh viewModel When collecting to NowPlaying Then assert expected value`() {
        runTest {
            // Given
            val movies = listOf<Movie>(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.NowPlaying.name
                )
            )
            // Valor do Duble
            whenever(repository.getNowPlaying()).thenReturn(Result.success(movies))
            //When
            val result = underTest.uiNowPlaying.take(2).toList()[1]

            //Then assert expected value
            val expected = MovieListUiState(
                list = listOf(
                    MovieUiData(
                        id = 1,
                        title = "title1",
                        overview = "overview1",
                        image = "image1"
                    )
                )
            )
            assertEquals(expected, result)
        }
    }

    @Test
    fun `Given fresh viewModel When collecting to PopularMovies Then assert expected value`() {
        runTest {
            // Given
            val movies = listOf<Movie>(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.Popular.name
                )
            )
            // Valor do Duble
            whenever(repository.getPopular()).thenReturn(Result.success(movies))
            //When
            val result = underTest.uiPopular.take(2).toList()[1]

            //Then assert expected value
            val expected = MovieListUiState(
                list = listOf(
                    MovieUiData(
                        id = 1,
                        title = "title1",
                        overview = "overview1",
                        image = "image1"
                    )
                )
            )
            assertEquals(expected, result)
        }
    }

    @Test
    fun `Given fresh viewModel When collecting to UpComing Then assert expected value`() {
        runTest {
            // Given
            val movies = listOf<Movie>(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.Upcoming.name
                )
            )
            // Valor do Duble
            whenever(repository.getPopular()).thenReturn(Result.success(movies))
            //When
            val result = underTest.uiUpComing.take(2).toList()[1]

            //Then assert expected value
            val expected = MovieListUiState(
                list = listOf(
                    MovieUiData(
                        id = 1,
                        title = "title1",
                        overview = "overview1",
                        image = "image1"
                    )
                )
            )
            assertEquals(expected, result)
        }
    }

    @Test
    fun `Given fresh viewModel When collecting to topRated Then assert loading state`() {
        runTest {
            // Given
            val movies = listOf<Movie>(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.TopRated.name
                )
            )
            // Valor do Duble
            whenever(repository.getTopRated()).thenReturn(Result.success(movies))
            //When
            val result = underTest.uiTopRated.value
            //Then assert expected value
            val expected = MovieListUiState(isLoading = true)
            assertEquals(expected, result)
        }
    }
}