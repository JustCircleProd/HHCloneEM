package com.justcircleprod.hhcloneem.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val ColorScheme = darkColorScheme(
    primary = Blue,
    secondary = Green,
    surface = Black
)

@Composable
fun HHCloneEMTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = Typography,
        content = content
    )
}