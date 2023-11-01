package com.futuristicmobileapps.tmdbkmm.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.futuristicmobileapps.tmdbkmm.models.Movie
import com.futuristicmobileapps.tmdbkmm.viewmodel.MainScreenViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {

                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val uiState = viewModel.uiState.collectAsState().value

                    when (uiState) {

                        is MainScreenViewModel.UiState.Data -> {
                            MovieList(movies = uiState.movies.data)
                        }

                        is MainScreenViewModel.UiState.Error -> {
                            Toast.makeText(LocalContext.current, "Some error!", Toast.LENGTH_SHORT).show()
                        }

                        MainScreenViewModel.UiState.Loading -> {
                            LoadingProgressBar()
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun MovieList(movies: List<Movie>) {

    LazyColumn(
        contentPadding = PaddingValues(12.dp)
    ) {
        movies.forEach {
            item { MovieCard(movie = it) }
        }
    }
}


@Composable
fun MovieCard(
    movie: Movie
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        elevation = 4.dp
    ) {

        Row(modifier = Modifier.fillMaxSize()) {

            Card(
                modifier = Modifier
                    .width(100.dp)
                    .height(120.dp),
                elevation = 4.dp
            ) {
                CoilImage(
                    imagePath = movie.imagePath,
                    contentDescription = movie.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                Text(
                    text = movie.description, style = MaterialTheme.typography.body1,
                    maxLines = 4, overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }

            Spacer(modifier = Modifier.width(4.dp))
        }

    }

}