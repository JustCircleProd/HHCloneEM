package com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository

import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.OfferModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.VacancyModel
import com.justcircleprod.hhcloneem.core.util.Resource
import kotlinx.coroutines.flow.StateFlow

interface OfferAndVacancyRepository {

    val offers: StateFlow<Resource<List<OfferModel>>>

    val vacancies: StateFlow<Resource<List<VacancyModel>>>

    suspend fun loadOffersAndVacanciesIfEmpty()
}