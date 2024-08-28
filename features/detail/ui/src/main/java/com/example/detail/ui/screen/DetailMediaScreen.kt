package com.example.detail.ui.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.detail.ui.viewmodel.DetailMediaViewModel
import com.example.detail.ui.widgets.DetailMediaContent

@Composable
fun DetailMediaScreen(
    viewModel: DetailMediaViewModel = hiltViewModel()
) {
    DetailMediaContent()
}