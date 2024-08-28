package com.example.ui.molecules

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.theme.NetflixTheme

@Composable
fun NetflixButton(
    text: String,
    onClick: () -> Unit,
    icon: ImageVector? = null,
    containerColor: Color = NetflixTheme.colors.onPrimary,
    contentColor: Color = NetflixTheme.colors.onSecondary,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = modifier
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        Text(text, color = contentColor)
    }
}

@Composable
@Preview
fun NetflixButtonWhitePreview() {
    NetflixTheme {
        NetflixButton(
            text = "Play",
            onClick = { }
        )
    }
}

@Composable
@Preview
fun NetflixButtonIconWhitePreview() {
    NetflixTheme {
        NetflixButton(
            text = "Play",
            onClick = { },
            icon = Icons.Default.PlayArrow,
            containerColor = NetflixTheme.colors.onPrimary,
            contentColor = NetflixTheme.colors.onSecondary
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
fun NetflixButtonIconWhiteOpacityPreview() {
    NetflixTheme {
        NetflixButton(
            text = "Play",
            onClick = { },
            icon = Icons.Default.PlayArrow,
            containerColor = NetflixTheme.colors.onPrimary.copy(alpha = .1F),
            contentColor = NetflixTheme.colors.onPrimary
        )
    }
}