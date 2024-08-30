package com.example.detail.data.mappers

import com.example.core.data.remote.model.MediaDetailResponse
import com.example.core.domain.model.MediaDetail
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class MediaDetailMapperTest {

    private lateinit var mediaDetailMapper: MediaDetailMapper

    private val dummyMediaDetailResponse = MediaDetailResponse(
        id = 1,
        title = "Movie 1",
        overview = "Overview 1",
        posterPath = "/poster1.jpg",
        releaseDate = "2021-01-01",
        videos = MediaDetailResponse.MediaVideoListResponse(
            results = listOf(
                MediaDetailResponse.MediaVideoResponse(
                    key = "key1",
                    official = true,
                    type = "Trailer"
                )
            )
        )
    )

    private val dummyMediaDetail = MediaDetail(
        id = 1,
        title = "Movie 1",
        overview = "Overview 1",
        posterPath = "https://image.tmdb.org/t/p/original/poster1.jpg",
        year = "2021",
        videoYoutubeKey = "key1"
    )

    @Before
    fun setUp() {
        mediaDetailMapper = MediaDetailMapper()
        mockkStatic(android.util.Log::class)
        every { android.util.Log.e(any(), any()) } returns 0
    }

    @Test
    fun `given valid MediaDetailResponse when map is called then it should return MediaDetail`() {
        val result = mediaDetailMapper.map(dummyMediaDetailResponse)
        assertEquals(dummyMediaDetail, result)
    }

    @Test
    fun `given MediaDetailResponse with missing id when map is called then it should throw IllegalArgumentException`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            mediaDetailMapper.map(dummyMediaDetailResponse.copy(id = null))
        }
        assertEquals("id is required", exception.message)
    }
}