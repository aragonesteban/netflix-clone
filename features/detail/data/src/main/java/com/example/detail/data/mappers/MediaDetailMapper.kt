package com.example.detail.data.mappers

import android.util.Log
import com.example.core.data.config.utils.NetflixMapper
import com.example.core.data.model.MediaDetailResponse
import com.example.core.data.utils.mapPosterImage
import com.example.core.domain.model.MediaDetail
import javax.inject.Inject

class MediaDetailMapper @Inject constructor() : NetflixMapper<MediaDetailResponse, MediaDetail> {

    override fun map(input: MediaDetailResponse): MediaDetail {
        return try {
            with(input) {
                MediaDetail(
                    id = requireNotNull(id) { "id is required" },
                    title = title ?: name ?: "",
                    overview = overview ?: "",
                    posterPath = posterPath.mapPosterImage("original"),
                    year = releaseDate?.let(::mapYear) ?: firstAirDate?.let(::mapYear) ?: "",
                    videoYoutubeKey = mapVideoYoutubeKey(videos?.results)
                )
            }
        } catch (e: IllegalArgumentException) {
            Log.e(TAG, "Error mapping MovieDetailResponse to MovieDetail: ${e.message}")
            throw e
        }
    }

    private fun mapVideoYoutubeKey(
        results: List<MediaDetailResponse.MediaVideoResponse>?
    ): String {
        return results
            ?.firstOrNull { it.official == true }
            ?.key
            ?: ""
    }

    private fun mapYear(releaseDate: String): String =
        releaseDate.split("-").first()

    private companion object {
        const val TAG = "MovieDetailMapper"
    }
}