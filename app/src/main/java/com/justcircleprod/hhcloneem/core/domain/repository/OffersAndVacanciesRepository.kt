package com.justcircleprod.hhcloneem.core.domain.repository

import com.justcircleprod.hhcloneem.core.domain.offersAndVacancies.model.OfferModel
import com.justcircleprod.hhcloneem.core.domain.offersAndVacancies.model.VacancyModel
import com.justcircleprod.hhcloneem.core.util.Resource

interface OffersAndVacanciesRepository {

    suspend fun getOffersAndVacancies(): Resource<Pair<List<OfferModel>, List<VacancyModel>>>
}