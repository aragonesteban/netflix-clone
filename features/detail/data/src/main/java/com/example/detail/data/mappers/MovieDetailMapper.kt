package com.example.detail.data.mappers

import android.util.Log
import com.example.core.data.config.utils.NetflixMapper
import com.example.core.data.model.MovieDetailResponse
import com.example.core.domain.model.MovieDetail
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() : NetflixMapper<MovieDetailResponse, MovieDetail> {

    override fun map(input: MovieDetailResponse): MovieDetail {
        return try {
            with(input) {
                MovieDetail(
                    id = requireNotNull(id) { "id is required" },
                    title = requireNotNull(title) { "title is required" },
                    overview = overview ?: "",
                    posterPath = "https://image.tmdb.org/t/p/original$posterPath",
                    year = releaseDate ?: "",
                    videoYoutubeKey = mapVideoYoutubeKey(videos?.results)
                )
            }
        } catch (e: IllegalArgumentException) {
            Log.e(TAG, "Error mapping MovieDetailResponse to MovieDetail: ${e.message}")
            throw e
        }
    }

    private fun mapVideoYoutubeKey(
        results: List<MovieDetailResponse.MovieVideoResponse>?
    ): String {
        return results
            ?.firstOrNull { it.official == true }
            ?.key
            ?: ""
    }

    private companion object {
        const val TAG = "MovieDetailMapper"
    }
}