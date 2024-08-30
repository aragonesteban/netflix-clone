package com.example.detail.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.domain.model.MediaDetail
import com.example.ui.molecules.NetflixButton
import com.example.ui.theme.NetflixTheme
import com.example.features.detail.ui.R
import com.example.core.ui.R as CoreUi

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
            text = stringResource(id = CoreUi.string.netflix_label_play),
            onClick = { onClickPlay() },
            icon = Icons.Default.PlayArrow,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(4.dp))

        NetflixButton(
            text = stringResource(id = R.string.netflix_label_download),
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
@Preview
fun DetailMediaInfoPreview() {
    NetflixTheme {
        DetailMediaInfo(
            mediaDetail = MediaDetail(
                id = 1,
                title = "Example Series",
                overview = "This is an example overview of the series.",
                year = "2023",
                posterPath = "https://example.com/poster.jpg",
                videoYoutubeKey = "exampleKey"
            ),
            onClickPlay = {}
        )
    }
}