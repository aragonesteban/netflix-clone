package com.example.detail.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.molecules.NetflixRemoteImage
import com.example.ui.theme.NetflixTheme

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
fun DetailMediaBackdropPreview() {
    NetflixTheme {
        DetailMediaBackdrop(poster = "posterUrl")
    }
}