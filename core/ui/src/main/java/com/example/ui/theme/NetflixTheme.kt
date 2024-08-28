package com.example.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = NetflixRed,
    onPrimary = NetflixWhite,
    primaryContainer = NetflixDarkGray,
    onPrimaryContainer = NetflixWhite,
    secondary = NetflixLightGray,
    onSecondary = NetflixBlack,
    background = NetflixBlack,
    onBackground = NetflixWhite,
    surface = NetflixDarkGray,
    onSurface = NetflixWhite,
)

private val LightColorPalette = lightColorScheme(
    primary = NetflixRed,
    onPrimary = NetflixWhite,
    primaryContainer = NetflixDarkGray,
    onPrimaryContainer = NetflixWhite,
    secondary = NetflixLightGray,
    onSecondary = NetflixBlack,
    background = NetflixWhite,
    onBackground = NetflixBlack,
    surface = NetflixLightGray,
    onSurface = NetflixBlack,
)

@Composable
fun NetflixTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors: ColorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = MaterialTheme.typography,
        content = content
    )
}