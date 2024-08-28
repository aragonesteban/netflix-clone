package com.example.home.data.mappers

import com.example.core.data.config.utils.NetflixMapper
import com.example.core.data.model.MediaResponse
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaList
import javax.inject.Inject

class MediaMapper @Inject constructor() : NetflixMapper<MediaResponse, MediaList> {

    override fun map(input: MediaResponse): MediaList =
        MediaList(
            items = input.results?.map {
                Media(
                    id = it.id ?: 0,
                    poster = "https://image.tmdb.org/t/p/w500${requireNotNull(it.posterPath)}",
                    title = it.title ?: it.name ?: ""
                )
            } ?: throw IllegalArgumentException("Movies list is null")
        )
}