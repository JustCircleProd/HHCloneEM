package com.justcircleprod.hhcloneem.favouriteVacancies.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.core.presentation.components.LoadingErrorScreen
import com.justcircleprod.hhcloneem.core.presentation.components.NavigationItem
import com.justcircleprod.hhcloneem.core.presentation.components.Text1
import com.justcircleprod.hhcloneem.core.presentation.components.Title2
import com.justcircleprod.hhcloneem.core.presentation.components.VacancyItem
import com.justcircleprod.hhcloneem.core.presentation.extensions.navigateSafety
import com.justcircleprod.hhcloneem.core.presentation.theme.Grey3
import com.justcircleprod.hhcloneem.core.presentation.theme.White

@Composable
fun FavouriteVacanciesScreen(navController: NavController) {
    val favouriteVacanciesViewModel = hiltViewModel<FavouriteVacanciesViewModel>()

    val isLoading by favouriteVacanciesViewModel.isLoading.collectAsStateWithLifecycle()
    val loadingError by favouriteVacanciesViewModel.loadingError.collectAsStateWithLifecycle()

    val favouriteVacancies by favouriteVacanciesViewModel.favouriteVacancies.collectAsStateWithLifecycle()

    if (isLoading || loadingError) {
        LoadingErrorScreen(
            isLoading = isLoading,
            loadingError = loadingError,
            onClick = { favouriteVacanciesViewModel.loadFavouriteVacancies() },
            modifier = Modifier.padding(dimensionResource(R.dimen.default_space))
        )

        return
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.default_space)),
        contentPadding = PaddingValues(bottom = dimensionResource(R.dimen.lazy_column_default_bottom_padding)),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Spacer(Modifier.height(dimensionResource(R.dimen.default_space)))

            FavouriteText()
        }

        item {
            VacanciesCountText(favouriteVacancies.size)
        }

        items(favouriteVacancies) {
            VacancyItem(
                vacancyModel = it,
                onClick = {
                    navController.navigateSafety(
                        currentRoute = NavigationItem.FavouriteVacancies.route,
                        targetRoute = NavigationItem.VacancyDetails.route
                    )
                },
                onLikeButtonClick = {
                    favouriteVacanciesViewModel.toggleFavouriteVacancy(it.id)
                },
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.default_space))
            )
        }
    }
}

@Composable
private fun FavouriteText() {
    Title2(
        text = stringResource(R.string.favourite),
        color = White,
        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.default_space))
    )
}

@Composable
private fun VacanciesCountText(vacanciesCount: Int) {
    Text1(
        text = pluralStringResource(
            R.plurals.vacancies,
            vacanciesCount,
            vacanciesCount
        ),
        color = Grey3,
        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.default_space))
    )
}