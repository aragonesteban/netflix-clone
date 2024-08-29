package com.example.core.data.repositories

import com.example.core.data.local.MediaLocalData
import com.example.core.data.remote.SeriesRemoteData
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import com.example.core.domain.model.MediaType
import com.example.core.domain.repositories.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val seriesRemoteData: SeriesRemoteData,
    private val mediaLocalData: MediaLocalData
) : SeriesRepository, BaseRepository() {

    override suspend fun getSeriesByCategory(
        category: String
    ): Flow<MediaList> = fetchData(
        localData = { mediaLocalData.getMediaList(category, MediaType.SERIES) },
        remoteData = { seriesRemoteData.getSeriesByCategory(category) },
        saveRemoteData = { mediaList ->
            mediaLocalData.insertMediaListItems(mediaList.items, category, MediaType.SERIES)
        }
    )

    override suspend fun getSeriesDetailById(
        seriesId: Int
    ): Flow<MediaDetail> = fetchData(
        localData = { mediaLocalData.getMediaDetailById(seriesId) },
        remoteData = { seriesRemoteData.getSeriesDetailById(seriesId) },
        saveRemoteData = { mediaDetail -> mediaLocalData.insertMediaDetail(mediaDetail) }
    )

    override suspend fun getSimilarSeriesById(seriesId: Int): Flow<MediaList> =
        seriesRemoteData.getSimilarSeriesById(seriesId)
            .catch { emit(MediaList(emptyList())) }
}