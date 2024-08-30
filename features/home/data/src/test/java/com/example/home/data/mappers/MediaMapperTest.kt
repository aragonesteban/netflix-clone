package com.example.home.data.mappers

import com.example.core.data.remote.model.MediaResponse
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaList
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class MediaMapperTest {

    private lateinit var mediaMapper: MediaMapper

    private val dummyMediaResponse = MediaResponse(
        results = listOf(
            MediaResponse.MediaItem(
                id = 1,
                posterPath = "/poster1.jpg",
                title = "Movie 1",
                name = null
            ),
            MediaResponse.MediaItem(
                id = 2,
                posterPath = "/poster2.jpg",
                title = null,
                name = "Series 1"
            )
        )
    )

    private val dummyMediaList = MediaList(
        items = listOf(
            Media(
                id = 1,
                poster = "https://image.tmdb.org/t/p/w500/poster1.jpg",
                title = "Movie 1"
            ),
            Media(
                id = 2,
                poster = "https://image.tmdb.org/t/p/w500/poster2.jpg",
                title = "Series 1"
            )
        )
    )

    @Before
    fun setUp() {
        mediaMapper = MediaMapper()
        mockkStatic(android.util.Log::class)
        every { android.util.Log.e(any(), any()) } returns 0
    }

    @Test
    fun `given valid MediaResponse when map is called then it should return MediaList`() {
        val result = mediaMapper.map(dummyMediaResponse)
        dummyMediaList.items.forEachIndexed { index, media ->
            assertEquals(media.id, result.items[index].id)
            assertEquals(media.poster, result.items[index].poster)
            assertEquals(media.title, result.items[index].title)
        }
    }

    @Test
    fun `given MediaResponse with missing results when map is called then it should throw IllegalArgumentException`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            mediaMapper.map(dummyMediaResponse.copy(results = null))
        }
        assertEquals("results are required", exception.message)
    }

    @Test
    fun `given MediaItemResponse with missing id when map is called then it should throw IllegalArgumentException`() {
        val invalidMediaResponse = dummyMediaResponse.copy(
            results = listOf(
                MediaResponse.MediaItem(
                    id = null,
                    posterPath = "/poster1.jpg",
                    title = "Movie 1",
                    name = null
                )
            )
        )
        val exception = assertThrows(IllegalArgumentException::class.java) {
            mediaMapper.map(invalidMediaResponse)
        }
        assertEquals("id is required", exception.message)
    }
}