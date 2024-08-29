package com.example.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration

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

var LocalNetflixColors = staticCompositionLocalOf {
    DarkColorPalette
}

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

    CompositionLocalProvider(LocalNetflixColors provides colors) {
        MaterialTheme(
            colorScheme = colors,
            content = content
        )
    }
}

object NetflixTheme {
    val colors: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalNetflixColors.current
}