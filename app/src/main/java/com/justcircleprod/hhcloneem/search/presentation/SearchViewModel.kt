package com.justcircleprod.hhcloneem.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justcircleprod.hhcloneem.core.common.Resource
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase.GetVacanciesAndOffersUseCase
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase.ToggleFavouriteVacancyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getVacanciesAndOffersUseCase: GetVacanciesAndOffersUseCase,
    private val toggleFavouriteVacancyUseCase: ToggleFavouriteVacancyUseCase
) : ViewModel() {

    private val vacanciesAndOffersFlow = getVacanciesAndOffersUseCase()

    val offers = vacanciesAndOffersFlow.map {
        if (it is Resource.Success && it.data != null) {
            it.data.first
        } else {
            emptyList()
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    val vacancies = vacanciesAndOffersFlow.map {
        if (it is Resource.Success && it.data != null) {
            it.data.second
        } else {
            emptyList()
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    val showAllVacancies = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            getVacanciesAndOffersUseCase.loadIfEmpty()
        }
    }

    fun setShowAllVacancies(value: Boolean) {
        showAllVacancies.value = value
    }

    fun toggleFavouriteVacancy(vacancyId: String) {
        viewModelScope.launch {
            toggleFavouriteVacancyUseCase.invoke(vacancyId)
        }
    }
}