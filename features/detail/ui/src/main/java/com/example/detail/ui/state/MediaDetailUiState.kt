package com.example.detail.ui.state

import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList

sealed interface MediaDetailUiState {
    data object Loading : MediaDetailUiState

    data class ShowMediaDetailContent(
        val mediaDetailContent: Pair<MediaDetail, MediaList>
    ) : MediaDetailUiState

    data class Error(val message: String) : MediaDetailUiState
}