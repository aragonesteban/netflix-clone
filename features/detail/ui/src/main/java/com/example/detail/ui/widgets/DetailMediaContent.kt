package com.example.detail.ui.widgets

import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import com.example.detail.ui.model.DetailMediaAction
import com.example.ui.theme.NetflixTheme
import kotlinx.coroutines.delay
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
    var showTrailer by rememberSaveable { mutableStateOf(playVideo) }
    var infoVisibility by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(200)
        infoVisibility = true
    }

    Box(modifier = modifier.fillMaxSize()) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S) {
            DetailMediaBackdrop(mediaDetail.posterPath)
        }

        AnimatedVisibility(visible = infoVisibility, enter = fadeIn()) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(32.dp))
                DetailMediaHeader(mediaDetail)
                DetailMediaInfo(mediaDetail) {
                    showTrailer = true
                }
                DetailMediaActions(
                    listOf(DetailMediaAction.Like, DetailMediaAction.Rate, DetailMediaAction.Share)
                )
                if (similarMovies.items.isNotEmpty()) {
                    DetailMediaRecommendation(
                        similarMedia = similarMovies,
                        onMediaClick = { mediaId -> onMediaClick(mediaId) }
                    )
                }
            }
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