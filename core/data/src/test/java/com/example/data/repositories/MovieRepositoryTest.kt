package com.example.data.repositories

import com.example.core.data.local.media.MediaLocalDataImpl
import com.example.core.data.remote.movies.MoviesRemoteData
import com.example.core.data.repositories.MoviesRepositoryImpl
import com.example.core.domain.model.MediaList
import com.example.core.domain.model.MediaType
import com.example.data.utils.dummyMediaDetail
import com.example.data.utils.dummyMediaList
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MovieRepositoryTest {

    private val moviesRemoteData: MoviesRemoteData = mockk(relaxed = true)
    private val mediaLocalData: MediaLocalDataImpl = mockk(relaxed = true)
    private lateinit var moviesRepository: MoviesRepositoryImpl

    @Before
    fun setUp() {
        moviesRepository = MoviesRepositoryImpl(moviesRemoteData, mediaLocalData)
    }

    @Test
    fun `given category when getMoviesByCategory is called then it should return MediaList from local data`() =
        runBlocking {
            val category = "popular"

            every { mediaLocalData.getMediaList(category, MediaType.MOVIES) } returns null
            coEvery {
                moviesRemoteData.getMoviesByCategory(category)
            } returns flow { emit(dummyMediaList) }

            val result = moviesRepository.getMoviesByCategory(category)

            result.collect { assert(it == dummyMediaList) }
            verify { mediaLocalData.getMediaList(category, MediaType.MOVIES) }
        }

    @Test
    fun `given category when getMoviesByCategory is called then it should return MediaList from remote data`() =
        runBlocking {
            val category = "popular"
            val mediaList = MediaList(emptyList())

            every { mediaLocalData.getMediaList(category, MediaType.MOVIES) } returns null
            coEvery { moviesRemoteData.getMoviesByCategory(category) } returns flow { emit(mediaList) }
            coEvery {
                mediaLocalData.insertMediaListItems(
                    mediaList.items,
                    category,
                    MediaType.MOVIES
                )
            } just Runs

            val result = moviesRepository.getMoviesByCategory(category)

            result.collect { assert(it == mediaList) }
            coVerify { moviesRemoteData.getMoviesByCategory(category) }
            coVerify {
                mediaLocalData.insertMediaListItems(
                    mediaList.items,
                    category,
                    MediaType.MOVIES
                )
            }
        }

    @Test
    fun `given movieId when getMovieDetailById is called then it should return MediaDetail from local data`() =
        runBlocking {
            val movieId = 1

            every { mediaLocalData.getMediaDetailById(movieId) } returns dummyMediaDetail

            val result = moviesRepository.getMovieDetailById(movieId)

            result.collect { assert(it == dummyMediaDetail) }
            verify { mediaLocalData.getMediaDetailById(movieId) }
        }

    @Test
    fun `given movieId when getMovieDetailById is called then it should return MediaDetail from remote data`() =
        runBlocking {
            val movieId = 1

            every { mediaLocalData.getMediaDetailById(movieId) } returns null
            coEvery { moviesRemoteData.getMovieDetailById(movieId) } returns flow {
                emit(dummyMediaDetail)
            }
            coEvery { mediaLocalData.insertMediaDetail(dummyMediaDetail) } just Runs

            val result = moviesRepository.getMovieDetailById(movieId)

            result.collect { assert(it == dummyMediaDetail) }
            coVerify { moviesRemoteData.getMovieDetailById(movieId) }
            coVerify { mediaLocalData.insertMediaDetail(dummyMediaDetail) }
        }

    @Test
    fun `given movieId when getSimilarMoviesById is called then it should return MediaList`() =
        runBlocking {
            val movieId = 1
            val mediaList = MediaList(emptyList())

            coEvery { moviesRemoteData.getSimilarMoviesById(movieId) } returns flow { emit(mediaList) }

            val result = moviesRepository.getSimilarMoviesById(movieId)

            result.collect { assert(it == mediaList) }
            coVerify { moviesRemoteData.getSimilarMoviesById(movieId) }
        }
}