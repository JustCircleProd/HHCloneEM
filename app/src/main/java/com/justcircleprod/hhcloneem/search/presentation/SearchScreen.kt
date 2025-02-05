package com.justcircleprod.hhcloneem.search.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.core.presentation.components.VacancyItem
import com.justcircleprod.hhcloneem.core.presentation.components.text.ButtonText1
import com.justcircleprod.hhcloneem.core.presentation.components.text.Text1
import com.justcircleprod.hhcloneem.core.presentation.components.text.Title2
import com.justcircleprod.hhcloneem.core.presentation.theme.Blue
import com.justcircleprod.hhcloneem.core.presentation.theme.White
import com.justcircleprod.hhcloneem.search.presentation.components.OfferItem
import com.justcircleprod.hhcloneem.search.presentation.components.SearchAndFiltersTopAppBar
import kotlinx.coroutines.launch

@Composable
fun SearchScreen() {
    val searchViewModel = hiltViewModel<SearchViewModel>()

    val offers by searchViewModel.offers.collectAsStateWithLifecycle()

    val vacancies by searchViewModel.vacancies.collectAsStateWithLifecycle()
    val showAllVacancies by searchViewModel.showAllVacancies.collectAsStateWithLifecycle()

    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        if (showAllVacancies) {
            SearchAndFiltersTopAppBar(
                searchHintStringRes = R.string.position_on_suitable_vacancies,
                onBackButtonClick = {
                    searchViewModel.setShowAllVacancies(false)
                },
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.screen_margin))
                    .padding(horizontal = dimensionResource(R.dimen.screen_margin))
            )

            Spacer(Modifier.height(19.dp))
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(19.dp),
            contentPadding = PaddingValues(bottom = 10.dp),
            state = lazyListState,
            modifier = Modifier.fillMaxSize()
        ) {
            if (!showAllVacancies) {
                item {
                    SearchAndFiltersTopAppBar(
                        searchHintStringRes = R.string.position_keywords,
                        modifier = Modifier
                            .padding(top = dimensionResource(R.dimen.screen_margin))
                            .padding(horizontal = dimensionResource(R.dimen.screen_margin))
                    )
                }
            }

            if (showAllVacancies) {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = dimensionResource(R.dimen.screen_margin)),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        VacanciesCountText(vacancies.size)

                        SortText()
                    }


                    Spacer(Modifier.height(11.dp))
                }
            } else {
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        item {
                            Spacer(Modifier.width(9.dp))
                        }

                        items(offers) {
                            OfferItem(it)
                        }

                        item {
                            Spacer(Modifier.width(9.dp))
                        }
                    }
                }

                item {
                    Spacer(Modifier.height(19.dp))

                    VacanciesForYouText()
                }
            }

            items(if (showAllVacancies) vacancies else vacancies.take(3)) {
                VacancyItem(
                    vacancyModel = it,
                    onLikeButtonClick = {
                        searchViewModel.toggleFavouriteVacancy(it.id)
                    },
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.screen_margin))
                )
            }

            if (!showAllVacancies) {
                item {
                    Spacer(Modifier.height(10.dp))

                    MoreVacanciesButton(
                        vacanciesCount = vacancies.size,
                        onClick = {
                            searchViewModel.setShowAllVacancies(true)

                            coroutineScope.launch {
                                lazyListState.animateScrollToItem(0)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun VacanciesForYouText() {
    Title2(
        text = stringResource(R.string.vacancies_for_you),
        color = White,
        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.screen_margin))
    )
}

@Composable
private fun MoreVacanciesButton(vacanciesCount: Int, onClick: () -> Unit) {
    TextButton(
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Blue,
            contentColor = White
        ),
        contentPadding = PaddingValues(vertical = 17.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.screen_margin)),
        onClick = onClick
    ) {
        ButtonText1(
            text = pluralStringResource(
                R.plurals.more_vacancies,
                vacanciesCount,
                vacanciesCount
            )
        )
    }
}

@Composable
private fun VacanciesCountText(vacanciesCount: Int) {
    Text1(
        text = pluralStringResource(
            R.plurals.vacancies,
            vacanciesCount,
            vacanciesCount
        ),
        color = White,
    )
}

@Composable
private fun SortText() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text1(
            text = stringResource(R.string.in_accordance),
            color = Blue
        )

        Spacer(Modifier.width(7.dp))

        Icon(
            painter = painterResource(R.drawable.icon_sort),
            contentDescription = stringResource(R.string.sort),
            tint = Blue,
            modifier = Modifier.size(19.dp)
        )
    }
}