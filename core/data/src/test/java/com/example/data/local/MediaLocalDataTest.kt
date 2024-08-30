package com.example.data.local

import com.example.core.data.local.database.MediaDao
import com.example.core.data.local.media.MediaLocalData
import com.example.core.data.local.media.MediaLocalDataImpl
import com.example.core.data.local.model.MediaDetailEntity
import com.example.core.data.local.model.MediaEntity
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import com.example.core.domain.model.MediaType
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class MediaLocalDataTest {

    private lateinit var mediaLocalData: MediaLocalData
    private val mediaDao: MediaDao = mockk<MediaDao>(relaxed = true)

    @Before
    fun setUp() {
        mediaLocalData = MediaLocalDataImpl(mediaDao)
    }


    @Test
    fun `given mediaList, category and mediaType when insertMediaListItems is called then mediaDao should call insertMediaListItems`() =
        runBlocking {
            val mediaList = listOf(
                Media(id = 1, poster = "poster1", title = "title1"),
                Media(id = 2, poster = "poster2", title = "title2")
            )
            val category = "category"
            val mediaType = MediaType.MOVIES

            coEvery { mediaDao.insertMediaListItems(any()) } just Runs

            mediaLocalData.insertMediaListItems(mediaList, category, mediaType)

            coVerify { mediaDao.insertMediaListItems(any()) }
        }

    @Test
    fun `given local data is available when getMediaList is called then it should return MediaList`() =
        runBlocking {
            val mediaEntities = listOf(
                MediaEntity(
                    mediaId = 1,
                    posterPath = "poster1",
                    title = "title1",
                    category = "category",
                    mediaType = "Movies"
                ),
                MediaEntity(
                    mediaId = 2,
                    posterPath = "poster2",
                    title = "title2",
                    category = "category",
                    mediaType = "Movies"
                )
            )

            val expectedMediaList = MediaList(
                items = listOf(
                    Media(id = 1, poster = "poster1", title = "title1"),
                    Media(id = 2, poster = "poster2", title = "title2")
                )
            )

            every { mediaDao.getMediaList() } returns mediaEntities

            val result = mediaLocalData.getMediaList("category", MediaType.MOVIES)

            expectedMediaList.items.forEachIndexed { index, media ->
                assertEquals(media.id, result?.items?.get(index)?.id)
                assertEquals(media.poster, result?.items?.get(index)?.poster)
                assertEquals(media.title, result?.items?.get(index)?.title)
            }
        }

    @Test
    fun `given local data is not available when getMediaList is called then it should return null`() =
        runBlocking {
            every { mediaDao.getMediaList() } returns emptyList()

            val result = mediaLocalData.getMediaList("category", MediaType.MOVIES)

            assertNull(result)
        }

    @Test
    fun `given mediaDetail when insertMediaDetail is called then mediaDao should call insertMediaDetail`() =
        runBlocking {
            val mediaDetail = MediaDetail(
                id = 1,
                title = "title",
                overview = "overview",
                posterPath = "posterPath",
                year = "year",
                videoYoutubeKey = "videoYoutubeKey"
            )

            coEvery { mediaDao.insertMediaDetail(any()) } just Runs

            mediaLocalData.insertMediaDetail(mediaDetail)

            coVerify { mediaDao.insertMediaDetail(any()) }
        }

    @Test
    fun `given local data is available when getMediaDetailById is called then it should return MediaDetail`() =
        runBlocking {
            val mediaDetailEntity = MediaDetailEntity(
                id = 1,
                title = "title",
                overview = "overview",
                posterPath = "posterPath",
                year = "year",
                videoYoutubeKey = "videoYoutubeKey"
            )
            val expectedMediaDetail = MediaDetail(
                id = 1,
                title = "title",
                overview = "overview",
                posterPath = "posterPath",
                year = "year",
                videoYoutubeKey = "videoYoutubeKey"
            )

            every { mediaDao.getMediaDetailById(1) } returns mediaDetailEntity

            val result = mediaLocalData.getMediaDetailById(1)

            assertEquals(expectedMediaDetail, result)
        }

    @Test
    fun `given local data is not available when getMediaDetailById is called then it should return null`() =
        runBlocking {
            every { mediaDao.getMediaDetailById(1) } returns null

            val result = mediaLocalData.getMediaDetailById(1)

            assertNull(result)
        }
}