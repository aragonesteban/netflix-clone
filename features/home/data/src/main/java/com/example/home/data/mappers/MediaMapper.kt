package com.example.home.data.mappers

import android.util.Log
import com.example.core.data.remote.model.MediaResponse
import com.example.core.data.utils.NetflixMapper
import com.example.core.data.utils.mapPosterImage
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaList
import javax.inject.Inject

class MediaMapper @Inject constructor() : NetflixMapper<MediaResponse, MediaList> {

    override fun map(input: MediaResponse): MediaList =
        try {
            MediaList(
                items = requireNotNull(input.results) { "results are required" }
                    .map {
                        Media(
                            id = requireNotNull(it.id) { "id is required" },
                            poster = it.posterPath.mapPosterImage("w500"),
                            title = it.title ?: it.name ?: ""
                        )
                    }
            )
        } catch (e: IllegalArgumentException) {
            Log.e(TAG, "Error mapping MediaResponse to MediaList: ${e.message}")
            throw e
        }

    private companion object {
        const val TAG = "MediaMapper"
    }
}