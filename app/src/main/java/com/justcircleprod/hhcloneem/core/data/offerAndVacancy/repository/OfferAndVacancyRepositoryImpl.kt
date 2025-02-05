package com.justcircleprod.hhcloneem.core.data.offerAndVacancy.repository

import com.justcircleprod.hhcloneem.core.common.Resource
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.dataSource.remote.OfferAndVacancyApi
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.mapper.mapToDomainModels
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.OfferModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.VacancyModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.OfferAndVacancyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class OfferAndVacancyRepositoryImpl(
    private val api: OfferAndVacancyApi
) : OfferAndVacancyRepository {

    private val _offers = MutableStateFlow<Resource<List<OfferModel>>>(Resource.Loading())
    override val offers: StateFlow<Resource<List<OfferModel>>> = _offers.asStateFlow()

    private val _vacancies = MutableStateFlow<Resource<List<VacancyModel>>>(Resource.Loading())
    override val vacancies: StateFlow<Resource<List<VacancyModel>>> = _vacancies.asStateFlow()

    override suspend fun loadOffersAndVacanciesIfEmpty() {
        withContext(Dispatchers.IO) {
            if (_offers.value is Resource.Success && _vacancies.value is Resource.Success) return@withContext

            try {
                _offers.value = Resource.Loading()
                _vacancies.value = Resource.Loading()

                val result = api.getOffersAndVacancies()

                _offers.value = Resource.Success(result.offers.mapToDomainModels())
                _vacancies.value = Resource.Success(result.vacancies.mapToDomainModels())
            } catch (e: Exception) {
                _offers.value = Resource.Error()
                _vacancies.value = Resource.Error()
            }
        }
    }
}