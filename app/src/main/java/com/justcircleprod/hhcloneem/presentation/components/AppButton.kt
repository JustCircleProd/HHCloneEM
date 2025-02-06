package com.justcircleprod.hhcloneem.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Shape
import com.justcircleprod.hhcloneem.presentation.theme.NoRippleConfiguration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScalableTextButton(
    shape: Shape,
    colors: ButtonColors,
    contentPadding: PaddingValues,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalRippleConfiguration provides NoRippleConfiguration) {
        val interactionSource = remember { MutableInteractionSource() }
        val scale by animateFloatAsState(
            targetValue = if (interactionSource.collectIsPressedAsState().value) 0.97f else 1f,
            animationSpec = tween(150),
            label = "Scale"
        )

        TextButton(
            interactionSource = interactionSource,
            shape = shape,
            colors = colors,
            contentPadding = contentPadding,
            onClick = onClick,
            modifier = modifier.scale(scale)
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScalableIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalRippleConfiguration provides NoRippleConfiguration) {
        val interactionSource = remember { MutableInteractionSource() }
        val scale by animateFloatAsState(
            targetValue = if (interactionSource.collectIsPressedAsState().value) 0.85f else 1f,
            animationSpec = tween(150),
            label = "Scale"
        )

        IconButton(
            interactionSource = interactionSource,
            onClick = onClick,
            modifier = modifier.scale(scale)
        ) {
            content()
        }
    }
}