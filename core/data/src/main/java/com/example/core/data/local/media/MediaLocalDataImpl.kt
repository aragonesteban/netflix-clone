package com.example.core.data.local.media

import com.example.core.data.local.database.MediaDao
import com.example.core.data.local.model.MediaDetailEntity
import com.example.core.data.local.model.MediaEntity
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import com.example.core.domain.model.MediaType
import javax.inject.Inject

class MediaLocalDataImpl @Inject constructor(
    private val mediaDao: MediaDao
): MediaLocalData {

    override fun insertMediaListItems(mediaList: List<Media>, category: String, mediaType: MediaType) {
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

    override fun getMediaList(category: String, mediaType: MediaType): MediaList? {
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

    override fun insertMediaDetail(mediaDetail: MediaDetail) {
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

    override fun getMediaDetailById(movieId: Int): MediaDetail? {
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