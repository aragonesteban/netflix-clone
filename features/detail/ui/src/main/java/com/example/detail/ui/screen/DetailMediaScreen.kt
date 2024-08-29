package com.example.detail.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.detail.ui.state.MediaDetailUiState
import com.example.detail.ui.viewmodel.DetailMediaViewModel
import com.example.detail.ui.widgets.DetailMediaContent
import com.example.ui.theme.NetflixTheme

@Composable
fun DetailMediaScreen(
    mediaId: Int,
    onBackPress: () -> Unit,
    viewModel: DetailMediaViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(true) {
        viewModel.getMovieDetailById(mediaId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = NetflixTheme.colors.background)
    ) {
        when (uiState) {
            MediaDetailUiState.Loading ->
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

            is MediaDetailUiState.ShowMediaDetailContent ->
                DetailMediaContent(
                    mediaDetail = uiState.mediaDetail,
                    onBackPress = onBackPress
                )

            MediaDetailUiState.Error -> {
                Text(text = "Error")
            }
        }
    }
}