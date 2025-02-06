package com.justcircleprod.hhcloneem.presentation.vacancyDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.presentation.components.NavigationItem
import com.justcircleprod.hhcloneem.presentation.components.Text1
import com.justcircleprod.hhcloneem.presentation.extensions.popBackStackSafety
import com.justcircleprod.hhcloneem.presentation.theme.Blue
import com.justcircleprod.hhcloneem.presentation.theme.White

@Composable
fun VacancyDetailScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.popBackStackSafety(NavigationItem.VacancyDetails.route)
            }
    ) {
        Text1(
            text = stringResource(R.string.vacancy_details),
            color = White
        )

        Text1(
            text = stringResource(R.string.back),
            color = Blue
        )
    }
}