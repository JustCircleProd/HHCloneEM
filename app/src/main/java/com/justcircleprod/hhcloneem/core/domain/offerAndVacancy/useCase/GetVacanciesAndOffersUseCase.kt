package com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase

import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.OfferModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.VacancyModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.OfferAndVacancyRepository
import com.justcircleprod.hhcloneem.core.util.Resource
import javax.inject.Inject

class GetVacanciesAndOffersUseCase @Inject constructor(
    private val offerAndVacancyRepository: OfferAndVacancyRepository
) {

    suspend operator fun invoke(): Resource<Pair<List<OfferModel>, List<VacancyModel>>> {
        return offerAndVacancyRepository.getOffersAndVacancies()
    }
}