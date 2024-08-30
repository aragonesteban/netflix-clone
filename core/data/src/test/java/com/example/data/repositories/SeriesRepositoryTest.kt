package com.example.data.repositories

import com.example.core.data.local.media.MediaLocalDataImpl
import com.example.core.data.remote.series.SeriesRemoteData
import com.example.core.data.repositories.SeriesRepositoryImpl
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import com.example.core.domain.model.MediaType
import com.example.data.utils.dummyMediaDetail
import io.mockk.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SeriesRepositoryTest {

    private val seriesRemoteData: SeriesRemoteData = mockk(relaxed = true)
    private val mediaLocalData: MediaLocalDataImpl = mockk(relaxed = true)
    private lateinit var seriesRepository: SeriesRepositoryImpl

    @Before
    fun setUp() {
        seriesRepository = SeriesRepositoryImpl(seriesRemoteData, mediaLocalData)
    }

    @Test
    fun `given category when getSeriesByCategory is called then it should return MediaList from local data`() =
        runBlocking {
            val category = "popular"
            val mediaList = MediaList(emptyList())

            every { mediaLocalData.getMediaList(category, MediaType.SERIES) } returns mediaList

            val result = seriesRepository.getSeriesByCategory(category)

            result.collect { assert(it == mediaList) }
            verify { mediaLocalData.getMediaList(category, MediaType.SERIES) }
        }

    @Test
    fun `given category when getSeriesByCategory is called then it should return MediaList from remote data`() =
        runBlocking {
            val category = "popular"
            val mediaList = MediaList(emptyList())

            every { mediaLocalData.getMediaList(category, MediaType.SERIES) } returns null
            coEvery { seriesRemoteData.getSeriesByCategory(category) } returns flow { emit(mediaList) }
            coEvery {
                mediaLocalData.insertMediaListItems(
                    mediaList.items,
                    category,
                    MediaType.SERIES
                )
            } just Runs

            val result = seriesRepository.getSeriesByCategory(category)

            result.collect { assert(it == mediaList) }
            coVerify { seriesRemoteData.getSeriesByCategory(category) }
            coVerify {
                mediaLocalData.insertMediaListItems(
                    mediaList.items,
                    category,
                    MediaType.SERIES
                )
            }
        }

    @Test
    fun `given seriesId when getSeriesDetailById is called then it should return MediaDetail from local data`() =
        runBlocking {
            val seriesId = 1

            every { mediaLocalData.getMediaDetailById(seriesId) } returns dummyMediaDetail

            val result = seriesRepository.getSeriesDetailById(seriesId)

            result.collect { assert(it == dummyMediaDetail) }
            verify { mediaLocalData.getMediaDetailById(seriesId) }
        }

    @Test
    fun `given seriesId when getSeriesDetailById is called then it should return MediaDetail from remote data`() =
        runBlocking {
            val seriesId = 1

            every { mediaLocalData.getMediaDetailById(seriesId) } returns null
            coEvery { seriesRemoteData.getSeriesDetailById(seriesId) } returns flow {
                emit(dummyMediaDetail)
            }
            coEvery { mediaLocalData.insertMediaDetail(dummyMediaDetail) } just Runs

            val result = seriesRepository.getSeriesDetailById(seriesId)

            result.collect { assert(it == dummyMediaDetail) }
            coVerify { seriesRemoteData.getSeriesDetailById(seriesId) }
            coVerify { mediaLocalData.insertMediaDetail(dummyMediaDetail) }
        }

    @Test
    fun `given seriesId when getSimilarSeriesById is called then it should return MediaList`() =
        runBlocking {
            val seriesId = 1
            val mediaList = MediaList(emptyList())

            coEvery { seriesRemoteData.getSimilarSeriesById(seriesId) } returns flow {
                emit(mediaList)
            }

            val result = seriesRepository.getSimilarSeriesById(seriesId)

            result.collect { assert(it == mediaList) }
            coVerify { seriesRemoteData.getSimilarSeriesById(seriesId) }
        }
}