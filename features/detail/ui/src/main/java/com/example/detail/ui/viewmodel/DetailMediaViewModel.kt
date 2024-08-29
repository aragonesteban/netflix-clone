package com.example.detail.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.detail.domain.usecases.MovieDetailUseCase
import com.example.detail.ui.state.MediaDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMediaViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MediaDetailUiState>(MediaDetailUiState.Loading)
    val uiState: StateFlow<MediaDetailUiState> = _uiState.asStateFlow()

    fun getMovieDetailById(movieId: Int) {
        viewModelScope.launch {
            movieDetailUseCase.getMovieDetailById(movieId)
                .onStart { _uiState.value = MediaDetailUiState.Loading }
                .catch { _uiState.value = MediaDetailUiState.Error }
                .collect { movieDetail ->
                    _uiState.value = MediaDetailUiState.ShowMediaDetailContent(movieDetail)
                }
        }
    }

}