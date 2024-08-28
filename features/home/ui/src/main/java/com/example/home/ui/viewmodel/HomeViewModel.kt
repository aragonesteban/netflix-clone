package com.example.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.usecases.MoviesUseCase
import com.example.home.domain.usecases.SeriesUseCase
import com.example.home.ui.model.HomeMediaContentUi
import com.example.home.ui.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    private val seriesUseCase: SeriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    private var moviesMediaContent: HomeMediaContentUi? = null
    private var seriesMediaContent: HomeMediaContentUi? = null

    fun getMovies() {
        viewModelScope.launch {
            moviesMediaContent?.let {
                _uiState.value = HomeUiState.ShowMediaContent(it)
            } ?: fetchContent(
                fetchContent = {
                    combine(
                        moviesUseCase.getPopularMovies(),
                        moviesUseCase.getUpcomingMovies()
                    ) { popular, upcoming -> HomeMediaContentUi(popular, upcoming) }
                },
                onSuccess = { moviesMediaContent = it }
            )
        }
    }

    fun getSeries() {
        viewModelScope.launch {
            seriesMediaContent?.let {
                _uiState.value = HomeUiState.ShowMediaContent(it)
            } ?: fetchContent(
                fetchContent = {
                    combine(
                        seriesUseCase.getPopularSeries(),
                        seriesUseCase.getTopRatedSeries()
                    ) { popular, topRated -> HomeMediaContentUi(popular, topRated) }
                },
                onSuccess = { seriesMediaContent = it }
            )
        }
    }

    private fun fetchContent(
        fetchContent: suspend () -> Flow<HomeMediaContentUi>,
        onSuccess: (HomeMediaContentUi) -> Unit
    ) {
        viewModelScope.launch {
            fetchContent()
                .onStart { _uiState.value = HomeUiState.Loading }
                .catch { _uiState.value = HomeUiState.Error }
                .collect {
                    onSuccess(it)
                    _uiState.value = HomeUiState.ShowMediaContent(it)
                }
        }
    }
}