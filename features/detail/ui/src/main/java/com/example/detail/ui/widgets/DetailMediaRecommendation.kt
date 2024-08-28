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
import com.example.ui.molecules.NetflixMediaCarousel
import com.example.ui.theme.NetflixTheme

enum class RecommendationType(val value: String) {
    Similar("Similar"),
    Trailers("Trailers")
}

@Composable
fun DetailMediaRecommendation(
    modifier: Modifier = Modifier
) {
    var currentRecommendation by rememberSaveable {
        mutableStateOf(RecommendationType.Similar)
    }

    Column(modifier = modifier) {
        DetailMediaRecommendationTabs(currentRecommendation) {
            currentRecommendation = it
        }

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
                )
            ),
            modifier = Modifier.padding(top = 12.dp)
        ) { }
    }
}

@Composable
fun DetailMediaRecommendationTabs(
    currentRecommendation: RecommendationType,
    modifier: Modifier = Modifier,
    onRecommendationSelected: (RecommendationType) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        RecommendationType.entries.forEach { recommendation ->
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
        DetailMediaRecommendation()
    }
}