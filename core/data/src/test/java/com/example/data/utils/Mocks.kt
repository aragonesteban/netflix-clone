package com.example.data.utils

import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList

val dummyMediaList = MediaList(
    items = listOf(
        Media(
            id = 1,
            poster = "/poster1.jpg",
            title = "Movie 1"
        ),
        Media(
            id = 2,
            poster = "/poster2.jpg",
            title = "Movie 2"
        ),
        Media(
            id = 3,
            poster = "/poster3.jpg",
            title = "Movie 3"
        )
    )
)

val dummyMediaDetail = MediaDetail(
    id = 1,
    title = "Movie 1",
    overview = "Overview 1",
    posterPath = "/poster1.jpg",
    year = "2021",
    videoYoutubeKey = "key1"
)
