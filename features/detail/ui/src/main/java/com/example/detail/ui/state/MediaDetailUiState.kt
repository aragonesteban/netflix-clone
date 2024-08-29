package com.example.detail.ui.state

import com.example.core.domain.model.MediaList
import com.example.core.domain.model.MediaDetail

sealed interface MediaDetailUiState {
    data object Loading : MediaDetailUiState

    data class ShowMediaDetailContent(
        val movieDetail: Pair<MediaDetail, MediaList>
    ) : MediaDetailUiState

    data class Error(val message: String) : MediaDetailUiState
}