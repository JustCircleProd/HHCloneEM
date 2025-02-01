package com.justcircleprod.hhcloneem.core.data.offersAndVacancies.repository

import com.justcircleprod.hhcloneem.core.data.offersAndVacancies.dataSource.remote.OffersAndVacanciesApi
import com.justcircleprod.hhcloneem.core.data.offersAndVacancies.mapper.mapToDomainModels
import com.justcircleprod.hhcloneem.core.domain.offersAndVacancies.model.OfferModel
import com.justcircleprod.hhcloneem.core.domain.offersAndVacancies.model.VacancyModel
import com.justcircleprod.hhcloneem.core.domain.repository.OffersAndVacanciesRepository
import com.justcircleprod.hhcloneem.core.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OffersAndVacanciesRepositoryImpl(
    private val api: OffersAndVacanciesApi
) : OffersAndVacanciesRepository {

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