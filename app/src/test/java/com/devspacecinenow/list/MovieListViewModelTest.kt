package com.devspacecinenow.list

import com.devspacecinenow.list.data.MovieListRepository
import com.devspacecinenow.list.presentation.ui.MovieListViewModel
import com.nhaarman.mockitokotlin2.mock


class MovieListViewModelTest {
    private val repository: MovieListViewModel.MovieListRepository = mock()

    private val underTest by lazy {
        MovieListViewModel(repository)

    }
}