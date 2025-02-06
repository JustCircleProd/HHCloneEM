package com.justcircleprod.hhcloneem.domain.useCase

import com.justcircleprod.hhcloneem.common.Resource
import com.justcircleprod.hhcloneem.domain.favouriteVacancy.repository.FavouriteVacancyRepository
import com.justcircleprod.hhcloneem.domain.offerAndVacancy.model.VacancyModel
import com.justcircleprod.hhcloneem.domain.offerAndVacancy.repository.OfferAndVacancyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetFavouriteVacanciesUseCase @Inject constructor(
    private val offerAndVacancyRepository: OfferAndVacancyRepository,
    private val favouriteVacancyRepository: FavouriteVacancyRepository
) {

    operator fun invoke(): Flow<Resource<List<VacancyModel>>> {
        return combine(
            offerAndVacancyRepository.vacancies,
            favouriteVacancyRepository.getAll()
        ) { vacancies, favouriteVacancyIds ->
            when {
                vacancies is Resource.Success && vacancies.data != null -> {
                    var favouriteVacancies = vacancies.data.filter { vacancy ->
                        favouriteVacancyIds.any { vacancy.id == it.vacancyId }
                    }

                    favouriteVacancies = favouriteVacancies.map { vacancy ->
                        vacancy.copy(
                            isFavorite = favouriteVacancyIds.any { vacancy.id == it.vacancyId }
                        )
                    }

                    Resource.Success(favouriteVacancies)
                }

                vacancies is Resource.Loading -> {
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