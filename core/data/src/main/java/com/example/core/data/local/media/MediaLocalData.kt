package com.example.core.data.local.media

import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import com.example.core.domain.model.MediaType

interface MediaLocalData {
    fun insertMediaListItems(
        mediaList: List<Media>,
        category: String,
        mediaType: MediaType
    )

    fun insertMediaDetail(mediaDetail: MediaDetail)

    fun getMediaList(
        category: String,
        mediaType: MediaType
    ): MediaList?

    fun getMediaDetailById(movieId: Int): MediaDetail?
}