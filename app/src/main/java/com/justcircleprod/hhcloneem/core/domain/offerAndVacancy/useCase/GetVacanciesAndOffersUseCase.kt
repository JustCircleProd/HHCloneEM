package com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase

import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.OfferModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.VacancyModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.FavouriteVacancyRepository
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.OfferAndVacancyRepository
import com.justcircleprod.hhcloneem.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetVacanciesAndOffersUseCase @Inject constructor(
    private val offerAndVacancyRepository: OfferAndVacancyRepository,
    private val favouriteVacancyRepository: FavouriteVacancyRepository
) {

    operator fun invoke(): Flow<Resource<Pair<List<OfferModel>, List<VacancyModel>>>> {
        return combine(
            offerAndVacancyRepository.offers,
            offerAndVacancyRepository.vacancies,
            favouriteVacancyRepository.getAll()
        ) { offers, vacancies, favouriteVacancyIds ->
            when {
                offers is Resource.Success && offers.data != null
                        && vacancies is Resource.Success && vacancies.data != null -> {
                    val newVacancies =
                        vacancies.data.map { vacancy ->
                            vacancy.copy(
                                isFavorite = favouriteVacancyIds.any { vacancy.id == it.vacancyId }
                            )
                        }

                    Resource.Success(Pair(offers.data, newVacancies))
                }

                offers is Resource.Loading && vacancies is Resource.Loading -> {
                    Resource.Loading()
                }

                else -> {
                    Resource.Error()
                }
            }
        }
    }

    suspend fun loadIfEmpty() {
        offerAndVacancyRepository.loadOffersAndVacanciesIfEmpty()
    }
}