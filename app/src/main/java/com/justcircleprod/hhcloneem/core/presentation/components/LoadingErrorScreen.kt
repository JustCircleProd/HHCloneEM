package com.justcircleprod.hhcloneem.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.core.presentation.theme.Blue
import com.justcircleprod.hhcloneem.core.presentation.theme.White

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
        modifier = modifier
            .fillMaxSize()
            .clickable { onClick() }
            .padding(dimensionResource(R.dimen.default_space))
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Blue)
            return
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