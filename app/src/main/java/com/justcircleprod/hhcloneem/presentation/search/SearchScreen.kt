package com.justcircleprod.hhcloneem.presentation.search

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.presentation.components.ButtonText1
import com.justcircleprod.hhcloneem.presentation.components.LoadingErrorScreen
import com.justcircleprod.hhcloneem.presentation.components.NavigationItem
import com.justcircleprod.hhcloneem.presentation.components.ScalableTextButton
import com.justcircleprod.hhcloneem.presentation.components.Text1
import com.justcircleprod.hhcloneem.presentation.components.Title2
import com.justcircleprod.hhcloneem.presentation.components.VacancyItem
import com.justcircleprod.hhcloneem.presentation.extensions.navigateSafety
import com.justcircleprod.hhcloneem.presentation.search.components.OfferItem
import com.justcircleprod.hhcloneem.presentation.search.components.SearchAndFiltersTopAppBar
import com.justcircleprod.hhcloneem.presentation.theme.Blue
import com.justcircleprod.hhcloneem.presentation.theme.White
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(navController: NavController) {
    val searchViewModel = hiltViewModel<SearchViewModel>()

    val isLoading by searchViewModel.isLoading.collectAsStateWithLifecycle()
    val loadingError by searchViewModel.loadingError.collectAsStateWithLifecycle()

    val offers by searchViewModel.offers.collectAsStateWithLifecycle()

    val vacancies by searchViewModel.vacancies.collectAsStateWithLifecycle()
    val showAllVacancies by searchViewModel.showAllVacancies.collectAsStateWithLifecycle()

    BackHandler(enabled = showAllVacancies) {
        if (showAllVacancies) searchViewModel.setShowAllVacancies(false)
    }

    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    if (isLoading || loadingError) {
        LoadingErrorScreen(
            isLoading = isLoading,
            loadingError = loadingError,
            onClick = { searchViewModel.loadOffersAndVacancies() },
            modifier = Modifier.padding(dimensionResource(R.dimen.default_space))
        )

        return
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (showAllVacancies) {
            SearchAndFiltersTopAppBar(
                searchHintStringRes = R.string.position_on_suitable_vacancies,
                onBackButtonClick = {
                    searchViewModel.setShowAllVacancies(false)
                },
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.default_space))
                    .padding(horizontal = dimensionResource(R.dimen.default_space))
            )

            Spacer(Modifier.height(dimensionResource(R.dimen.default_space)))
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.default_space)),
            contentPadding = PaddingValues(bottom = dimensionResource(R.dimen.lazy_column_default_bottom_padding)),
            state = lazyListState,
            modifier = Modifier.fillMaxSize()
        ) {
            if (!showAllVacancies) {
                item {
                    SearchAndFiltersTopAppBar(
                        searchHintStringRes = R.string.position_keywords,
                        modifier = Modifier
                            .padding(top = dimensionResource(R.dimen.default_space))
                            .padding(horizontal = dimensionResource(R.dimen.default_space))
                    )
                }
            }

            if (showAllVacancies) {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = dimensionResource(R.dimen.default_space)),
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
                    Spacer(Modifier.height(dimensionResource(R.dimen.default_space)))

                    VacanciesForYouText()
                }
            }

            items(if (showAllVacancies) vacancies else vacancies.take(3)) {
                VacancyItem(
                    vacancyModel = it,
                    onClick = {
                        navController.navigateSafety(
                            currentRoute = NavigationItem.Search.route,
                            targetRoute = NavigationItem.VacancyDetails.route
                        )
                    },
                    onLikeButtonClick = {
                        searchViewModel.toggleFavouriteVacancy(it.id)
                    },
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.default_space))
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
        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.default_space))
    )
}

@Composable
private fun MoreVacanciesButton(vacanciesCount: Int, onClick: () -> Unit) {
    ScalableTextButton(
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Blue,
            contentColor = White
        ),
        contentPadding = PaddingValues(vertical = 17.dp),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.default_space)),
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
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {  }
    ) {
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