package com.justcircleprod.hhcloneem.core.data.offerAndVacancy.repository

import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.dataSource.remote.OfferAndVacancyApi
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.mapper.mapToDomainModels
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.OfferModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.VacancyModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.OfferAndVacancyRepository
import com.justcircleprod.hhcloneem.core.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OfferAndVacancyRepositoryImpl(
    private val api: OfferAndVacancyApi
) : OfferAndVacancyRepository {

    override suspend fun getOffersAndVacancies(): Resource<Pair<List<OfferModel>, List<VacancyModel>>> {
        return withContext(Dispatchers.IO) {
            try {
                val result = api.getOffersAndVacancies()

                Resource.Success(
                    Pair(
                        result.offers.mapToDomainModels(),
                        result.vacancies.mapToDomainModels()
                    )
                )
            } catch (e: Exception) {
                Resource.Error(error = true)
            }
        }
    }
}