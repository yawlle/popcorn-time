package com.example.popcorntime.home.presentation

import app.cash.turbine.test
import com.example.popcorntime.domain.use_cases.GetMoviesByIdUseCase
import com.example.popcorntime.presentation.moviePage.MoviePageState
import com.example.popcorntime.presentation.moviePage.MoviePageViewModel
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviePageViewModelTest {

    private lateinit var moviePageViewModel: MoviePageViewModel

    private val getMoviesByIdUseCase: GetMoviesByIdUseCase = mockk(relaxed = true)

    private val moviePageState = MoviePageState()

    @Before
    fun setup() {
        moviePageViewModel = MoviePageViewModel(getMoviesByIdUseCase)
    }

    @Test
    fun `WHEN ViewModel is initialized THEN moviePageState is MoviePageState()`() =
        runTest {
            moviePageViewModel.moviePageState.test {
                assertEquals(expectMostRecentItem(), MoviePageState())
            }
        }

    @Test
    fun `WHEN onBtnBackClicked() THEN _btnBackClicked send true`() =
        runTest {
           moviePageViewModel.onBtnBackClick()

           moviePageViewModel.btnBackClicked.test {
                assertEquals(awaitItem(), true)
                cancelAndIgnoreRemainingEvents()
            }

        }
}