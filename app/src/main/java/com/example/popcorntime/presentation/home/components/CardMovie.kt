package com.example.popcorntime.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.popcorntime.R
import com.example.popcorntime.domain.model.MovieSummary

@Composable
fun CardMovieComponent(movie: MovieSummary) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(movie.poster)
            .size(Size.ORIGINAL)
            .placeholder(R.drawable.placeholder_movie)
            .fallback(R.drawable.placeholder_movie)
            .error(R.drawable.placeholder_movie)
            .build()
    )

    Row(
        modifier = Modifier
            .fillMaxWidth(), verticalAlignment = Alignment.Bottom
    ) {
        Image(
            painter = painter,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(110.dp, 140.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentDescription = ""
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = RoundedCornerShape(bottomEnd = 16.dp, topEnd = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.white)
            )
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Row {
                    Text(
                        text = movie.type,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "-",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                    )
                    Text(
                        text = movie.year,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                    )
                }
//                Row {
//                    Text(
//                        text = "IMDB: ",
//                        style = MaterialTheme.typography.labelSmall,
//                        fontWeight = FontWeight.Normal,
//                        modifier = Modifier.padding(bottom = 4.dp)
//                    )
//                    Text(
//                        text = "5.6/10 ",
//                        style = MaterialTheme.typography.labelSmall,
//                        fontWeight = FontWeight.Bold,
//                        modifier = Modifier.padding(bottom = 4.dp)
//                    )
//                }
            }

        }
    }
}

@Preview
@Composable
fun CardMoviePreview() {

}