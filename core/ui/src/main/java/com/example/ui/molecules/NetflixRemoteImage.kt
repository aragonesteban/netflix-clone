package com.example.ui.molecules

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.core.ui.R

@Composable
fun NetflixRemoteImage(
    url: String,
    contentScale: ContentScale,
    modifier: Modifier = Modifier
) {
    if (LocalInspectionMode.current) {
        Image(
            painter = painterResource(id = R.drawable.local_image),
            contentDescription = "",
            contentScale = contentScale,
            modifier = modifier
        )
    } else {
        AsyncImage(
            model = url,
            contentDescription = "",
            contentScale = contentScale,
            modifier = modifier,
        )
    }
}