package com.example.features.detail.ui.viewmodel

import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import com.example.core.domain.model.MediaType
import com.example.detail.domain.usecases.MediaDetailUseCase
import com.example.detail.ui.state.MediaDetailUiState
import com.example.detail.ui.viewmodel.DetailMediaViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailMediaViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: DetailMediaViewModel
    private val mediaDetailUseCase: MediaDetailUseCase = mockk()

    private val dummyMediaDetail = MediaDetail(
        id = 1,
        title = "Movie 1",
        overview = "Overview 1",
        posterPath = "https://image.tmdb.org/t/p/original/poster1.jpg",
        year = "2021",
        videoYoutubeKey = "key1"
    )

    private val dummyMediaList = MediaList(
        items = listOf(
            Media(
                id = 1,
                poster = "https://image.tmdb.org/t/p/w500/poster1.jpg",
                title = "Movie 1"
            )
        )
    )

    @Before
    fun setUp() {
        viewModel = DetailMediaViewModel(mediaDetailUseCase)
    }

    @Test
    fun `given movieId when getMediaDetailById is called then it should return MediaDetail and MediaList`() =
        runBlocking {
            val mediaId = 1
            val mediaType = MediaType.MOVIES

            coEvery { mediaDetailUseCase.getMovieDetailById(mediaId) } returns flow {
                emit(dummyMediaDetail)
            }
            coEvery { mediaDetailUseCase.getSimilarMoviesById(mediaId) } returns flow {
                emit(dummyMediaList)
            }

            viewModel.getMediaDetailById(mediaId, mediaType)

            val expectedState =
                MediaDetailUiState.ShowMediaDetailContent(dummyMediaDetail to dummyMediaList)
            assertEquals(expectedState, viewModel.uiState.value)
        }

    @Test
    fun `given seriesId when getMediaDetailById is called then it should return MediaDetail and MediaList`() =
        runBlocking {
            val mediaId = 1
            val mediaType = MediaType.SERIES

            coEvery { mediaDetailUseCase.getSeriesDetailById(mediaId) } returns flow {
                emit(dummyMediaDetail)
            }
            coEvery { mediaDetailUseCase.getSimilarSeriesById(mediaId) } returns flow {
                emit(dummyMediaList)
            }

            viewModel.getMediaDetailById(mediaId, mediaType)

            val expectedState =
                MediaDetailUiState.ShowMediaDetailContent(dummyMediaDetail to dummyMediaList)
            assertEquals(expectedState, viewModel.uiState.value)
        }
}