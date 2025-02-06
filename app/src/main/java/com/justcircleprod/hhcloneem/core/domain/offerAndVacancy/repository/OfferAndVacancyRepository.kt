package com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository

import com.justcircleprod.hhcloneem.core.common.Resource
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.OfferModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.VacancyModel
import kotlinx.coroutines.flow.StateFlow

interface OfferAndVacancyRepository {
    // to cache data within a session
    val offers: StateFlow<Resource<List<OfferModel>>>
    val vacancies: StateFlow<Resource<List<VacancyModel>>>

    suspend fun loadOffersAndVacanciesIfEmpty()
}