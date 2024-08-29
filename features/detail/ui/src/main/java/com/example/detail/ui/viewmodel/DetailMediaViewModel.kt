package com.example.detail.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import com.example.core.domain.model.MediaType
import com.example.detail.domain.usecases.MediaDetailUseCase
import com.example.detail.ui.state.MediaDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMediaViewModel @Inject constructor(
    private val mediaDetailUseCase: MediaDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MediaDetailUiState>(MediaDetailUiState.Loading)
    val uiState: StateFlow<MediaDetailUiState> = _uiState.asStateFlow()

    private var mediaDetailContent: Pair<MediaDetail, MediaList>? = null

    fun getMediaDetailById(mediaId: Int, mediaType: MediaType) {
        viewModelScope.launch {
            mediaDetailContent?.let {
                _uiState.value = MediaDetailUiState.ShowMediaDetailContent(it)
            } ?: fetchMediaDetail(mediaId = mediaId, mediaType)
                .onStart { _uiState.value = MediaDetailUiState.Loading }
                .catch { _uiState.value = MediaDetailUiState.Error(it.message.toString()) }
                .collect { mediaDetail ->
                    mediaDetailContent = mediaDetail
                    _uiState.value = MediaDetailUiState.ShowMediaDetailContent(mediaDetail)
                }
        }
    }

    private suspend fun fetchMediaDetail(
        mediaId: Int,
        mediaType: MediaType
    ): Flow<Pair<MediaDetail, MediaList>> {
        return when (mediaType) {
            MediaType.MOVIES -> combine(
                mediaDetailUseCase.getMovieDetailById(mediaId),
                mediaDetailUseCase.getSimilarMoviesById(mediaId)
            ) { detail, similar -> detail to similar }

            MediaType.SERIES -> combine(
                mediaDetailUseCase.getSeriesDetailById(mediaId),
                mediaDetailUseCase.getSimilarSeriesById(mediaId)
            ) { detail, similar -> detail to similar }
        }
    }
}