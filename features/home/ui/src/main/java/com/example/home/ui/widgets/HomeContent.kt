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
import com.example.home.ui.model.HomeContentType
import com.example.home.ui.model.HomeMediaContentUi
import com.example.ui.theme.NetflixTheme

@Composable
fun HomeContent(
    mediaContent: HomeMediaContentUi,
    currentContentType: HomeContentType,
    onContentTypeSelected: (HomeContentType) -> Unit,
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
                item { HomeHero(movie = content.firstCategoryMediaList.items.first()) }

                item {
                    HomeCarousel(
                        itemsHome = content.firstCategoryMediaList.items.drop(1),
                        title = "Popular movies",
                        modifier = Modifier.padding(top = 22.dp)
                    ) {
                        // Handle carousel item clicks here
                    }
                }

                item {
                    HomeCarousel(
                        itemsHome = content.secondCategoryMediaList.items,
                        title = "Now Playing Movies",
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        // Handle carousel item clicks here
                    }
                }
            }
        }

        HomeContentActions(
            modifier = Modifier.align(Alignment.TopCenter),
            currentContentType = currentContentType,
            onContentTypeSelected = onContentTypeSelected
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
            currentContentType = HomeContentType.Movies,
            onContentTypeSelected = { },
        )
    }
}