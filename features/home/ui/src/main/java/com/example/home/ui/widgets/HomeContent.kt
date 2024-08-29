package com.example.home.ui.widgets

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaList
import com.example.core.domain.model.MediaType
import com.example.home.ui.model.HomeMediaContentUi
import com.example.ui.molecules.NetflixMediaCarousel
import com.example.ui.theme.NetflixTheme

@Composable
fun HomeContent(
    mediaContent: HomeMediaContentUi,
    currentMediaType: MediaType,
    onMediaTypeSelected: (MediaType) -> Unit,
    onMediaClick: (Int, Boolean) -> Unit
) {
    Box {
        AnimatedContent(
            targetState = mediaContent,
            transitionSpec = { fadeIn().togetherWith(fadeOut()) },
            label = "mediaContent"
        ) { content ->
            LazyColumn(
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    HomeHero(
                        movie = content.firstCategoryMediaList.items.first(),
                        onMediaClick = onMediaClick
                    )
                }

                item {
                    NetflixMediaCarousel(
                        itemsHome = content.firstCategoryMediaList.items.drop(1),
                        title = "Popular movies",
                        modifier = Modifier.padding(top = 22.dp)
                    ) { onMediaClick(it.id, false) }
                }

                item {
                    NetflixMediaCarousel(
                        itemsHome = content.secondCategoryMediaList.items,
                        title = "Upcoming Movies",
                        modifier = Modifier.padding(top = 8.dp)
                    ) { onMediaClick(it.id, false) }
                }
            }
        }

        HomeContentActions(
            modifier = Modifier.align(Alignment.TopCenter),
            currentMediaType = currentMediaType,
            onMediaTypeSelected = onMediaTypeSelected
        )
    }
}

@Composable
@Preview
fun HomeContentPreview() {
    NetflixTheme {
        HomeContent(
            mediaContent = HomeMediaContentUi(
                firstCategoryMediaList = MediaList(
                    items = listOf(
                        Media(
                            id = 1,
                            title = "Movie 1",
                            poster = ""
                        ),
                        Media(
                            id = 2,
                            title = "Movie 2",
                            poster = ""
                        )
                    )
                ),
                secondCategoryMediaList = MediaList(
                    items = listOf(
                        Media(
                            id = 2,
                            title = "Movie 2",
                            poster = ""
                        )
                    )
                )
            ),
            currentMediaType = MediaType.MOVIES,
            onMediaTypeSelected = { },
            onMediaClick = { _, _ -> },
        )
    }
}