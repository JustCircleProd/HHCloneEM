package com.justcircleprod.hhcloneem.responses.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.core.presentation.components.Text1
import com.justcircleprod.hhcloneem.core.presentation.theme.White

@Composable
fun ResponsesScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text1(
            text = stringResource(R.string.responses),
            color = White
        )
    }
}