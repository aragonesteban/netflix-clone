package com.example.core.data.repositories

import com.example.core.data.local.media.MediaLocalDataImpl
import com.example.core.data.remote.movies.MoviesRemoteData
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import com.example.core.domain.model.MediaType
import com.example.core.domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteData: MoviesRemoteData,
    private val mediaLocalData: MediaLocalDataImpl
) : MoviesRepository, BaseRepository() {

    override suspend fun getMoviesByCategory(
        category: String
    ): Flow<MediaList> = fetchData(
        localData = { mediaLocalData.getMediaList(category, MediaType.MOVIES) },
        remoteData = { moviesRemoteData.getMoviesByCategory(category) },
        saveRemoteData = { mediaList ->
            mediaLocalData.insertMediaListItems(mediaList.items, category, MediaType.MOVIES)
        }
    )

    override suspend fun getMovieDetailById(
        movieId: Int
    ): Flow<MediaDetail> = fetchData(
        localData = { mediaLocalData.getMediaDetailById(movieId) },
        remoteData = { moviesRemoteData.getMovieDetailById(movieId) },
        saveRemoteData = { mediaDetail -> mediaLocalData.insertMediaDetail(mediaDetail) }
    )

    override suspend fun getSimilarMoviesById(movieId: Int): Flow<MediaList> =
        moviesRemoteData.getSimilarMoviesById(movieId)
            .catch { emit(MediaList(emptyList())) }
}