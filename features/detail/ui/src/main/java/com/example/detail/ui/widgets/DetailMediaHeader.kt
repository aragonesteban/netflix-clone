package com.example.detail.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.domain.model.MediaDetail
import com.example.ui.molecules.NetflixRemoteImage
import com.example.ui.theme.NetflixTheme

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
        color = NetflixTheme.colors.onPrimary,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}


@Composable
@Preview
fun DetailMediaHeaderPreview() {
    NetflixTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailMediaHeader(
                mediaDetail = MediaDetail(
                    id = 1,
                    title = "Example Movie",
                    overview = "This is an example overview of the movie.",
                    year = "2023",
                    posterPath = "https://example.com/poster.jpg",
                    videoYoutubeKey = "exampleKey"
                )
            )
        }
    }
}