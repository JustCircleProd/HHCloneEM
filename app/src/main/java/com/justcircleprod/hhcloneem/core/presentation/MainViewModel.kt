package com.justcircleprod.hhcloneem.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase.GetFavouriteVacanciesCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getFavouriteVacanciesCountUseCase: GetFavouriteVacanciesCountUseCase
) : ViewModel() {

    val favouriteVacanciesCount = getFavouriteVacanciesCountUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            0
        )
}