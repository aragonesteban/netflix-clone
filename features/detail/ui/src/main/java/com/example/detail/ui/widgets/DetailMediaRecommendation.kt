package com.example.detail.ui.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaList
import com.example.detail.ui.model.DetailRecommendationType
import com.example.ui.molecules.NetflixMediaCarousel
import com.example.ui.theme.NetflixTheme

@Composable
fun DetailMediaRecommendation(
    modifier: Modifier = Modifier,
    similarMedia: MediaList,
    onMediaClick: (Int) -> Unit
) {
    var currentRecommendation by rememberSaveable {
        mutableStateOf(
            if (similarMedia.items.isNotEmpty()) {
                DetailRecommendationType.Similar
            } else {
                DetailRecommendationType.Trailers
            }
        )
    }

    Column(modifier = modifier) {
        DetailMediaRecommendationTabs(
            currentRecommendation = currentRecommendation,
            showSimilarTab = similarMedia.items.isNotEmpty()
        ) {
            currentRecommendation = it
        }
        if (similarMedia.items.isNotEmpty()) {
            NetflixMediaCarousel(
                itemsHome = similarMedia.items,
                modifier = Modifier.padding(top = 12.dp)
            ) {
                onMediaClick(it.id)
            }
        }
    }
}

@Composable
fun DetailMediaRecommendationTabs(
    currentRecommendation: DetailRecommendationType,
    showSimilarTab: Boolean,
    modifier: Modifier = Modifier,
    onRecommendationSelected: (DetailRecommendationType) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        val recommendations = if (showSimilarTab) {
            DetailRecommendationType.entries
        } else {
            DetailRecommendationType.entries.filter { it != DetailRecommendationType.Similar }
        }

        recommendations.forEach { recommendation ->
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .height(30.dp)
                    .padding(end = 8.dp)
                    .clickable { onRecommendationSelected(recommendation) }
            ) {
                AnimatedVisibility(
                    visible = currentRecommendation == recommendation,
                    enter = expandHorizontally(),
                    exit = shrinkHorizontally()
                ) {
                    Box(
                        modifier = Modifier
                            .background(NetflixTheme.colors.primary)
                            .fillMaxWidth()
                            .height(4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = recommendation.value,
                    color = NetflixTheme.colors.onPrimary,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
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