package com.example.ui.molecules

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.domain.model.Media
import com.example.ui.theme.NetflixTheme

@Composable
fun NetflixMediaCarousel(
    itemsHome: List<Media>,
    title: String? = null,
    modifier: Modifier = Modifier,
    onItemClicked: (Media) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        title?.let {
            Text(
                text = it,
                color = NetflixTheme.colors.onPrimary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            items(itemsHome) { item ->
                NetflixRemoteImage(
                    url = item.poster,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(110.dp)
                        .height(170.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .clickable { onItemClicked(item) }
                )
            }
        }
    }
}

@Composable
@Preview
fun HomeCarouselPreview() {
    NetflixTheme {
        NetflixMediaCarousel(
            itemsHome = listOf(
                Media(
                    id = 1,
                    title = "Movie 1",
                    poster = ""
                ),
                Media(
                    id = 2,
                    title = "Movie 2",
                    poster = ""
                ),
                Media(
                    id = 3,
                    title = "Movie 3",
                    poster = ""
                ),
                Media(
                    id = 4,
                    title = "Movie 4",
                    poster = ""
                )
            ),
            title = "Popular Movies",
        ) { }
    }
}