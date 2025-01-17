package com.example.popcorntime.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.popcorntime.R
import com.example.popcorntime.domain.model.MovieSummary
import com.example.popcorntime.presentation.common.ErrorScreen
import com.example.popcorntime.presentation.common.ScreenState
import com.example.popcorntime.presentation.home.components.CardMovieComponent
import com.example.popcorntime.presentation.home.components.NoResultsFoundComponent
import com.example.popcorntime.presentation.home.components.SearchBar
import com.example.popcorntime.presentation.ui.Nunito

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onMovieClicked: (String) -> Unit,
) {

    val screenState by viewModel.screenState.collectAsState()
    val listMovies by viewModel.filteredMoviesList.collectAsState()
    val searchingValue by viewModel.searchValue.collectAsState()
    val showNoResults by viewModel.showNoResults.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background_app)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            HeaderHome(
                searchingValue,
                viewModel::changeSearchValue,
                viewModel::onSearchButtonClicked
            )

            when (screenState) {
                is ScreenState.Error -> {
                    ErrorScreen()
                }

                is ScreenState.Content -> {
                    ScreenContent(
                        listMovies = listMovies,
                        showNoResults = showNoResults,
                        onMovieClicked = onMovieClicked
                    )
                }

                is ScreenState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        color = colorResource(R.color.secondary_color)
                    )
                }
            }
        }
    }

}

@Composable
private fun ScreenContent(
    listMovies: List<MovieSummary>,
    showNoResults: Boolean,
    onMovieClicked: (String) -> Unit
) {

    Column(Modifier.verticalScroll(rememberScrollState())) {
        listMovies.forEach { movie ->
            CardMovieComponent(movie = movie, onMovieClicked = { onMovieClicked(movie.imdbID) })
            Spacer(modifier = Modifier.padding(top = 20.dp))
        }
    }

    if (showNoResults) {
        NoResultsFoundComponent()
    }

}

@Composable
private fun HeaderHome(
    searchingValue: String,
    onValueChange: (String) -> Unit,
    onSearchButtonClicked: () -> Unit
) {

    Text(
        text = stringResource(R.string.title_home),
        color = Color.Black,
        fontFamily = Nunito,
        fontWeight = FontWeight.Bold,
        lineHeight = 29.sp,
        letterSpacing = 0.sp,
        style = MaterialTheme.typography.headlineLarge,
    )

    Spacer(modifier = Modifier.padding(top = 20.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .weight(0.8f),
            value = searchingValue,
            hint = stringResource(R.string.text_hint_search),
            onValueChange = { onValueChange(it) }
        )
        Text(
            modifier = Modifier
                .weight(0.2f)
                .clickable {
                    onSearchButtonClicked()
                },
            text = stringResource(R.string.text_search_btn),
            style = MaterialTheme.typography.labelLarge,
        )
    }

    Spacer(modifier = Modifier.padding(top = 20.dp))
    Text(
        text = stringResource(R.string.text_subtitle),
        color = Color.Black,
        fontFamily = Nunito,
        fontWeight = FontWeight.Normal,
        lineHeight = 21.sp,
        letterSpacing = 0.sp,
        style = MaterialTheme.typography.headlineSmall
    )
    Spacer(modifier = Modifier.padding(top = 20.dp))

}