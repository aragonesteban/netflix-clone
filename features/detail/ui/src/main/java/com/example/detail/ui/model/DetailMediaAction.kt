package com.example.detail.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.features.detail.ui.R

sealed class DetailMediaAction(val text: Int, val icon: ImageVector) {
    data object Like : DetailMediaAction(R.string.netflix_label_like, Icons.Default.FavoriteBorder)
    data object Rate : DetailMediaAction(R.string.netflix_label_rate, Icons.Default.StarRate)
    data object Share : DetailMediaAction(R.string.netflix_label_share, Icons.Default.Share)
}