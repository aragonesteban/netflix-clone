package com.example.detail.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaList
import com.example.features.detail.ui.R
import com.example.ui.molecules.NetflixMediaCarousel
import com.example.ui.theme.NetflixTheme

@Composable
fun DetailMediaRecommendation(
    modifier: Modifier = Modifier,
    similarMedia: MediaList,
    onMediaClick: (Int) -> Unit
) {
    Column(modifier = modifier) {

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(30.dp)
                .padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(NetflixTheme.colors.primary)
                    .fillMaxWidth()
                    .height(4.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.netflix_subtitle_more_like_this),
                color = NetflixTheme.colors.onPrimary,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        NetflixMediaCarousel(
            itemsHome = similarMedia.items,
            modifier = Modifier.padding(top = 12.dp)
        ) {
            onMediaClick(it.id)
        }
    }
}

@Composable
@Preview
fun DetailMediaSuggestedPreview() {
    NetflixTheme {
        DetailMediaRecommendation(
            similarMedia = MediaList(
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
            onMediaClick = {}
        )
    }
}