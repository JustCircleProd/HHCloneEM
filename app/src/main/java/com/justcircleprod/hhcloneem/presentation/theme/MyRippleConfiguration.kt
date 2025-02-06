package com.justcircleprod.hhcloneem.presentation.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RippleConfiguration
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
val MyRippleConfiguration = RippleConfiguration(
    color = White,
    rippleAlpha = RippleAlpha(0.2f, 0.2f, 0.2f, 0.2f)
)

@OptIn(ExperimentalMaterial3Api::class)
val NoRippleConfiguration = RippleConfiguration(
    color = Color.Transparent,
    rippleAlpha = RippleAlpha(0.0f, 0.0f, 0.0f, 0.0f)
)