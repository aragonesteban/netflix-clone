package com.example.detail.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.detail.ui.model.DetailMediaAction
import com.example.ui.theme.NetflixTheme

@Composable
fun DetailMediaActions(
    actions: List<DetailMediaAction>,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)

    ) {
        actions.forEach { action ->
            DetailMediaActionsItem(action = action)
        }
    }
}

@Composable
fun DetailMediaActionsItem(
    action: DetailMediaAction,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Icon(
            imageVector = action.icon,
            contentDescription = action.text,
            tint = NetflixTheme.colors.onPrimary
        )
        Text(
            text = action.text,
            color = NetflixTheme.colors.onPrimary,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
@Preview
fun DetailMediaActionsPreview() {
    NetflixTheme {
        DetailMediaActions(
            listOf(DetailMediaAction.Like, DetailMediaAction.Rate, DetailMediaAction.Share)
        )
    }
}