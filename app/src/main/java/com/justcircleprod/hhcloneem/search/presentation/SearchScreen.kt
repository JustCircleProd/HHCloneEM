package com.justcircleprod.hhcloneem.search.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.search.presentation.components.OfferItem
import com.justcircleprod.hhcloneem.search.presentation.components.SearchTopBar

@Composable
fun SearchScreen() {
    val searchViewModel = hiltViewModel<SearchViewModel>()

    val offers by searchViewModel.offers.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Spacer(Modifier.height(dimensionResource(R.dimen.screen_margin)))

            SearchTopBar(modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.screen_margin)))
        }

        item {
            Spacer(Modifier.height(19.dp))

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
    }
}