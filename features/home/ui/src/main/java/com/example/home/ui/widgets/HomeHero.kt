package com.example.home.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.domain.model.Media
import com.example.ui.molecules.NetflixButton
import com.example.ui.molecules.NetflixRemoteImage
import com.example.ui.theme.NetflixTheme

@Composable
fun HomeHero(
    movie: Media,
    modifier: Modifier = Modifier,
    onMediaClick: (mediaId: Int, playVideo: Boolean) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(530.dp)
    ) {
        NetflixRemoteImage(
            url = movie.poster,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black)
                    )
                )
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = movie.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = NetflixTheme.colors.onPrimary,
                modifier = Modifier.padding(horizontal = 22.dp)
            )

            HomeHeroActions(
                onMediaClick = { playVideo -> onMediaClick(movie.id, playVideo) },
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun HomeHeroActions(
    modifier: Modifier = Modifier,
    onMediaClick: (playVideo: Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {

        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "",
                tint = NetflixTheme.colors.onPrimary
            )
        }

        NetflixButton(
            text = "Play",
            onClick = { onMediaClick(true) },
            icon = Icons.Default.PlayArrow,
            containerColor = NetflixTheme.colors.onPrimary,
            contentColor = NetflixTheme.colors.onSecondary
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable { onMediaClick(false) }
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "Info",
                modifier = Modifier.size(16.dp),
                tint = NetflixTheme.colors.onPrimary
            )
            Text("Info", color = NetflixTheme.colors.onPrimary, fontSize = 12.sp)
        }
    }
}

@Composable
@Preview
fun HomeHeroPreview() {
    NetflixTheme {
        HomeHero(
            movie = Media(
                id = 0,
                poster = "https://image.tmdb.org/t/p/w500/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                title = "The Tomorrow War"
            ),
            onMediaClick = { _, _ -> }
        )
    }
}
