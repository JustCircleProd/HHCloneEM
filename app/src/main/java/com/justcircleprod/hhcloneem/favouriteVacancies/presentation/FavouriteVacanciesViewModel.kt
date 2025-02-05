package com.justcircleprod.hhcloneem.favouriteVacancies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase.GetFavouriteVacanciesUseCase
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase.ToggleFavouriteVacancyUseCase
import com.justcircleprod.hhcloneem.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteVacanciesViewModel @Inject constructor(
    private val getFavouriteVacanciesUseCase: GetFavouriteVacanciesUseCase,
    private val toggleFavouriteVacancyUseCase: ToggleFavouriteVacancyUseCase
) : ViewModel() {

    val favouriteVacancies = getFavouriteVacanciesUseCase().map {
        if (it is Resource.Success && it.data != null) {
            it.data
        } else {
            emptyList()
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    init {
        viewModelScope.launch {
            getFavouriteVacanciesUseCase.loadIfEmpty()
        }
    }

    fun toggleFavouriteVacancy(vacancyId: String) {
        viewModelScope.launch {
            toggleFavouriteVacancyUseCase.invoke(vacancyId)
        }
    }
}