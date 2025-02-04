package com.justcircleprod.hhcloneem.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.OfferModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.VacancyModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase.GetVacanciesAndOffersUseCase
import com.justcircleprod.hhcloneem.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getVacanciesAndOffersUseCase: GetVacanciesAndOffersUseCase
) : ViewModel() {

    val offers = MutableStateFlow<List<OfferModel>>(emptyList())

    val vacancies = MutableStateFlow<List<VacancyModel>>(emptyList())

    val showAllVacancies = MutableStateFlow(false)

    init {
        loadOffersAndVacancies()
    }

    private fun loadOffersAndVacancies() {
        viewModelScope.launch {
            val result = getVacanciesAndOffersUseCase()

            if (result is Resource.Success && result.data != null) {
                offers.value = result.data.first
                vacancies.value = result.data.second
            }
        }
    }

    fun setShowAllVacancies(value: Boolean) {
        showAllVacancies.value = value
    }
}