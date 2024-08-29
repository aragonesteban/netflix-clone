package com.example.home.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaType
import com.example.home.ui.state.HomeUiState
import com.example.home.ui.viewmodel.HomeViewModel
import com.example.home.ui.widgets.HomeContent
import com.example.ui.screens.ErrorRetryScreen
import com.example.ui.theme.NetflixTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onMediaClick: (Int, MediaType, Boolean) -> Unit,
    onGetMediaContent: (MediaType) -> Unit
) {
    var currentMediaType: MediaType by rememberSaveable {
        mutableStateOf(MediaType.MOVIES)
    }

    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(Unit) {
        onGetMediaContent(currentMediaType)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = NetflixTheme.colors.background)
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
                        currentMediaType = currentMediaType,
                        onMediaTypeSelected = { contentType ->
                            currentMediaType = contentType
                            onGetMediaContent(currentMediaType)
                        },
                        onMediaClick = { mediaId, playVideo ->
                            onMediaClick(mediaId, currentMediaType, playVideo)
                        }
                    )
                }
            }

            is HomeUiState.Error -> {
                ErrorRetryScreen { onGetMediaContent(currentMediaType) }
            }
        }
    }
}