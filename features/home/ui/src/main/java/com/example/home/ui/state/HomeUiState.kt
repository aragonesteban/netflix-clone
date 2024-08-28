package com.example.home.ui.state

import com.example.home.ui.model.HomeMediaContentUi

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class ShowMediaContent(
        val mediaContent: HomeMediaContentUi
    ) : HomeUiState
    data object Error : HomeUiState
}