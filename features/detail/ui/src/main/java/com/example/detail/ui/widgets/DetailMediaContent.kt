package com.example.detail.ui.widgets

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaList
import com.example.core.domain.model.MediaDetail
import com.example.detail.ui.model.DetailMediaAction
import com.example.ui.molecules.NetflixButton
import com.example.ui.molecules.NetflixRemoteImage
import com.example.ui.theme.NetflixTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMediaContent(
    mediaDetail: MediaDetail,
    similarMovies: MediaList,
    playVideo: Boolean,
    onBackPress: () -> Unit,
    onMediaClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showTrailer by remember { mutableStateOf(playVideo) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S) {
            DetailMediaBackdrop(mediaDetail.posterPath)
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = 32.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailMediaHeader(mediaDetail)
            DetailMediaInfo(mediaDetail) {
                showTrailer = true
            }
            DetailMediaActions(
                listOf(DetailMediaAction.Like, DetailMediaAction.Rate, DetailMediaAction.Share)
            )
            DetailMediaRecommendation(
                similarMedia = similarMovies,
                onMediaClick = { mediaId -> onMediaClick(mediaId) }
            )
        }

        IconButton(
            onClick = { onBackPress() },
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "",
                tint = NetflixTheme.colors.onPrimary
            )
        }

        if (showTrailer) {
            DetailMediaTrailer(
                trailerYoutubeId = mediaDetail.videoYoutubeKey,
                sheetState = sheetState
            ) {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showTrailer = false
                    }
                }
            }
        }
    }
}

@Composable
fun DetailMediaHeader(
    mediaDetail: MediaDetail
) {
    NetflixRemoteImage(
        url = mediaDetail.posterPath,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .width(130.dp)
            .height(190.dp)
            .clip(RoundedCornerShape(6.dp))
    )

    Text(
        text = mediaDetail.title,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onPrimary,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Composable
fun DetailMediaInfo(
    mediaDetail: MediaDetail,
    modifier: Modifier = Modifier,
    onClickPlay: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(
                text = mediaDetail.year,
                color = NetflixTheme.colors.onPrimary.copy(alpha = .4F),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.size(12.dp))

        NetflixButton(
            text = "Play",
            onClick = { onClickPlay() },
            icon = Icons.Default.PlayArrow,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(4.dp))

        NetflixButton(
            text = "Download",
            onClick = { },
            icon = Icons.Default.Download,
            contentColor = NetflixTheme.colors.onPrimary,
            containerColor = NetflixTheme.colors.onPrimary.copy(alpha = .1F),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(12.dp))

        Text(
            text = mediaDetail.overview,
            color = NetflixTheme.colors.onPrimary,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun DetailMediaBackdrop(
    poster: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(470.dp)
    ) {
        NetflixRemoteImage(
            url = poster,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .blur(radiusX = 50.dp, radiusY = 50.dp)
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
    }
}

@Composable
@Preview
fun DetailMediaContentPreview() {
    NetflixTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(NetflixTheme.colors.background)
        ) {
            DetailMediaContent(
                mediaDetail = MediaDetail(
                    id = 1,
                    title = "Deadpool & Wolverine",
                    overview = "A listless Wade Wilson toils away in civilian life with his days as the morally flexible mercenary, Deadpool, behind him. But when his homeworld faces an existential threat,",
                    year = "2023",
                    posterPath = "",
                    videoYoutubeKey = ""
                ),
                onBackPress = {},
                similarMovies = MediaList(
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
                onMediaClick = {},
                playVideo = false
            )
        }
    }
}