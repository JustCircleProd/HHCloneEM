package com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase

import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.VacancyModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.FavouriteVacancyRepository
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.OfferAndVacancyRepository
import com.justcircleprod.hhcloneem.core.util.Resource
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