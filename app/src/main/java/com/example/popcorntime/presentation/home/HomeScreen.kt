package com.example.popcorntime.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.popcorntime.R
import com.example.popcorntime.presentation.home.components.CardMovieComponent
import com.example.popcorntime.presentation.home.components.SearchBar
import com.example.popcorntime.presentation.ui.Nunito

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {

    val screenState = viewModel.screenState.collectAsState()
    val listMovies = viewModel.filteredMoviesList.collectAsState()


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background_app)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Welcome to PopcornTime!",
                color = Color.Black,
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold,
                lineHeight = 29.sp,
                letterSpacing = 0.sp,
                style = MaterialTheme.typography.headlineLarge,
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = "Subtitle",
                color = Color.Black,
                fontFamily = Nunito,
                fontWeight = FontWeight.Normal,
                lineHeight = 21.sp,
                letterSpacing = 0.sp,
                style = MaterialTheme.typography.headlineMedium,

                )
            Spacer(modifier = Modifier.padding(top = 20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBar(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .weight(0.8f),
                    value = "",
                    hint = "Search for movies or series",
                    onValueChange = {  }
                )
                Text(
                    modifier = Modifier
                        .weight(0.2f)
                        .clickable {  },
                    text = "Search",
                    style = MaterialTheme.typography.labelLarge,
                )
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            listMovies.value.forEach { movie ->
                CardMovieComponent(movie = movie)
            }

        }
    }
}



@Preview
@Composable
fun HomeScreenPreview() {

}