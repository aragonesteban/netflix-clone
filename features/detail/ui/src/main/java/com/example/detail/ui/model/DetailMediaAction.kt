package com.example.detail.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DetailMediaAction(val text: String, val icon: ImageVector) {
    data object Like : DetailMediaAction("Like", Icons.Default.FavoriteBorder)
    data object Rate : DetailMediaAction("Rate", Icons.Default.StarRate)
    data object Share : DetailMediaAction("Share", Icons.Default.Share)
}