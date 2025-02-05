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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.core.presentation.components.Text1
import com.justcircleprod.hhcloneem.core.presentation.components.Title2
import com.justcircleprod.hhcloneem.core.presentation.components.VacancyItem
import com.justcircleprod.hhcloneem.core.presentation.theme.Grey3
import com.justcircleprod.hhcloneem.core.presentation.theme.White

@Composable
fun FavouriteVacanciesScreen() {
    val favouriteVacanciesViewModel = hiltViewModel<FavouriteVacanciesViewModel>()

    val favouriteVacancies by favouriteVacanciesViewModel.favouriteVacancies.collectAsStateWithLifecycle()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(19.dp),
        contentPadding = PaddingValues(bottom = 10.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Spacer(Modifier.height(19.dp))

            FavouriteText()
        }

        item {
            VacanciesCountText(favouriteVacancies.size)
        }

        items(favouriteVacancies) {
            VacancyItem(
                vacancyModel = it,
                onLikeButtonClick = {
                    favouriteVacanciesViewModel.toggleFavouriteVacancy(it.id)
                },
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.screen_margin))
            )
        }
    }
}

@Composable
private fun FavouriteText() {
    Title2(
        text = stringResource(R.string.favourite),
        color = White,
        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.screen_margin))
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
        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.screen_margin))
    )
}