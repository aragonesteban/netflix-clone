package com.example.detail.ui.state

import com.example.core.domain.model.MovieDetail

sealed interface MediaDetailUiState {
    data object Loading : MediaDetailUiState

    data class ShowMediaDetailContent(
        val mediaDetail: MovieDetail
    ) : MediaDetailUiState

    data object Error : MediaDetailUiState
}