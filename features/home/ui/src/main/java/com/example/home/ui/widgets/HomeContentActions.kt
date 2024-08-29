package com.example.home.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.domain.model.MediaType
import com.example.ui.theme.NetflixTheme

@Composable
fun HomeContentActions(
    currentMediaType: MediaType,
    modifier: Modifier = Modifier,
    onMediaTypeSelected: (MediaType) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {
        listOf(MediaType.MOVIES, MediaType.SERIES).forEach { content ->
            OutlinedButton(
                onClick = { onMediaTypeSelected(content) },
                contentPadding = PaddingValues(horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (currentMediaType == content)
                        MaterialTheme.colorScheme.onPrimary.copy(alpha = .4F)
                    else
                        Color.Transparent
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            ) {
                Text(
                    text = content.value,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                )
            }
        }
    }
}

@Composable
@Preview
fun HomeContentActionsPreview() {
    NetflixTheme {
        HomeContentActions(
            currentMediaType = MediaType.MOVIES
        ) {}
    }
}