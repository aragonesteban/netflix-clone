package com.example.detail.ui.widgets

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.detail.ui.model.DetailMediaAction
import com.example.ui.molecules.NetflixButton
import com.example.ui.molecules.NetflixRemoteImage
import com.example.ui.theme.NetflixTheme

@Composable
fun DetailMediaContent(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = { },
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

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = 32.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailMediaHeader()
            DetailMediaInfo()
            DetailMediaActions(
                listOf(DetailMediaAction.Like, DetailMediaAction.Rate, DetailMediaAction.Share)
            )
            DetailMediaRecommendation()
        }
    }
}

@Composable
fun DetailMediaHeader(
    modifier: Modifier = Modifier
) {
    NetflixRemoteImage(
        url = "",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .padding(bottom = 16.dp)
            .width(130.dp)
            .height(190.dp)
            .clip(RoundedCornerShape(6.dp))
    )

    Text(
        text = "Movie Title",
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onPrimary
    )
}

@Composable
fun DetailMediaInfo(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Row {
            Text(
                text = "2023",
                color = NetflixTheme.colors.onPrimary.copy(alpha = .4F),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.size(12.dp))

        NetflixButton(
            text = "Play",
            onClick = { /*TODO*/ },
            icon = Icons.Default.PlayArrow,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(4.dp))

        NetflixButton(
            text = "Download",
            onClick = { /*TODO*/ },
            icon = Icons.Default.Download,
            contentColor = NetflixTheme.colors.onPrimary,
            containerColor = NetflixTheme.colors.onPrimary.copy(alpha = .1F),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(12.dp))

        Text(
            text = "A listless Wade Wilson toils away in civilian life with his days as the morally flexible mercenary, Deadpool, behind him. But when his homeworld faces an existential threat,",
            color = NetflixTheme.colors.onPrimary,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
@Preview
fun DetailMediaContentPreview() {
    NetflixTheme {
        DetailMediaContent()
    }
}