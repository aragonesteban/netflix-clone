package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Replay
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.molecules.NetflixButton
import com.example.ui.theme.NetflixTheme

@Composable
fun ErrorRetryScreen(onRetry: () -> Unit) {
    NetflixTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(NetflixTheme.colors.background)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Outlined.ErrorOutline,
                    contentDescription = "Error Icon",
                    modifier = Modifier.size(64.dp),
                    tint = NetflixTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "An error occurred, please try again",
                    style = MaterialTheme.typography.bodyLarge,
                    color = NetflixTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.height(16.dp))
                NetflixButton(
                    text = "Retry",
                    onClick = onRetry,
                    icon = Icons.Outlined.Replay
                )
            }
        }
    }
}

@Composable
@Preview
fun ErrorRetryScreenPreview() {
    NetflixTheme {
        ErrorRetryScreen(
            onRetry = { }
        )
    }
}