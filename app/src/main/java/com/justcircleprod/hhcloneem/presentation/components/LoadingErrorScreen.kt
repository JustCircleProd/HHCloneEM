package com.justcircleprod.hhcloneem.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.presentation.theme.Blue
import com.justcircleprod.hhcloneem.presentation.theme.White

@Composable
fun LoadingErrorScreen(
    isLoading: Boolean,
    loadingError: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }
            .then(modifier)
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Blue)
            return@Column
        }

        if (loadingError) {
            Title3(
                text = stringResource(R.string.loading_error),
                color = White,
            )

            Title3(
                text = stringResource(R.string.refresh),
                color = Blue
            )
        }
    }
}