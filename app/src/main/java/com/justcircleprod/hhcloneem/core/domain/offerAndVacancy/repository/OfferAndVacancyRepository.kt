package com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository

import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.OfferModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.VacancyModel
import com.justcircleprod.hhcloneem.core.util.Resource

interface OfferAndVacancyRepository {

    suspend fun getOffersAndVacancies(): Resource<Pair<List<OfferModel>, List<VacancyModel>>>
}