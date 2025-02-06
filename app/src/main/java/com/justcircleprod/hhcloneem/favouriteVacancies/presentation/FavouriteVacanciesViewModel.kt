package com.justcircleprod.hhcloneem.favouriteVacancies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justcircleprod.hhcloneem.core.common.Resource
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase.GetFavouriteVacanciesUseCase
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase.ToggleFavouriteVacancyUseCase
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

    private val favouriteVacanciesFlow = getFavouriteVacanciesUseCase()

    val isLoading = favouriteVacanciesFlow.map { it is Resource.Loading }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            true
        )

    val loadingError = favouriteVacanciesFlow.map { it is Resource.Error }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            false
        )

    val favouriteVacancies = favouriteVacanciesFlow.map {
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
        loadFavouriteVacancies()
    }

    fun loadFavouriteVacancies() {
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