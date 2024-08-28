package com.example.home.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.home.ui.model.HomeContentType
import com.example.home.ui.state.HomeUiState
import com.example.home.ui.viewmodel.HomeViewModel
import com.example.home.ui.widgets.HomeContent

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value

    var currentContentType: HomeContentType by remember {
        mutableStateOf(HomeContentType.Movies)
    }

    LaunchedEffect(true) {
        viewModel.getMovies()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        when (uiState) {
            is HomeUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is HomeUiState.ShowMediaContent -> {
                uiState.run {
                    HomeContent(
                        mediaContent = mediaContent,
                        currentContentType = currentContentType,
                        onContentTypeSelected = { contentType ->
                            currentContentType = contentType
                            when (contentType) {
                                HomeContentType.Movies -> viewModel.getMovies()
                                HomeContentType.Series -> viewModel.getSeries()
                            }
                        }
                    )
                }
            }

            is HomeUiState.Error -> {
                Text(text = "Error")
            }
        }
    }
}