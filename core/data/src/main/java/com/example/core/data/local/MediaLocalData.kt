package com.example.core.data.local

import com.example.core.data.local.database.MediaDao
import com.example.core.data.local.model.MediaDetailEntity
import com.example.core.data.local.model.MediaEntity
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import com.example.core.domain.model.MediaType
import javax.inject.Inject

class MediaLocalData @Inject constructor(
    private val mediaDao: MediaDao
) {

    fun insertMediaListItems(mediaList: List<Media>, category: String, mediaType: MediaType) {
        val moviesItemsEntityList = mediaList.map { movie ->
            MediaEntity(
                mediaId = movie.id,
                posterPath = movie.poster,
                title = movie.title,
                category = category,
                mediaType = mediaType.value
            )
        }
        mediaDao.insertMediaListItems(moviesItemsEntityList)
    }

    fun getMediaList(category: String, mediaType: MediaType): MediaList? {
        val localMediaList = mediaDao.getMediaList()
            .filter { it.category == category && it.mediaType == mediaType.value }

        return if (localMediaList.isNotEmpty()) {
            MediaList(
                items = localMediaList.map { movie ->
                    Media(
                        id = movie.mediaId,
                        poster = movie.posterPath,
                        title = movie.title
                    )
                }
            )
        } else {
            null
        }
    }

    fun insertMediaDetail(mediaDetail: MediaDetail) {
        val mediaDetailEntity = MediaDetailEntity(
            id = mediaDetail.id,
            title = mediaDetail.title,
            overview = mediaDetail.overview,
            posterPath = mediaDetail.posterPath,
            year = mediaDetail.year,
            videoYoutubeKey = mediaDetail.videoYoutubeKey
        )
        mediaDao.insertMediaDetail(mediaDetailEntity)
    }

    fun getMediaDetailById(movieId: Int): MediaDetail? {
        val movieDetailEntity = mediaDao.getMediaDetailById(movieId)
        return movieDetailEntity?.run {
            MediaDetail(
                id = id,
                title = title,
                overview = overview,
                posterPath = posterPath,
                year = year,
                videoYoutubeKey = videoYoutubeKey
            )
        }
    }
}