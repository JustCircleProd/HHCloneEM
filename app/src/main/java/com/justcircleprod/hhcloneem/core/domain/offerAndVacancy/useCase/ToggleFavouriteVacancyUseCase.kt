package com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase

import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.FavouriteVacancyModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.FavouriteVacancyRepository
import javax.inject.Inject

class ToggleFavouriteVacancyUseCase @Inject constructor(
    private val favouriteVacancyRepository: FavouriteVacancyRepository
) {

    suspend operator fun invoke(vacancyId: String) {
        if (favouriteVacancyRepository.isFavourite(vacancyId)) {
            favouriteVacancyRepository.remove(vacancyId)
        } else {
            favouriteVacancyRepository.add(FavouriteVacancyModel(vacancyId))
        }
    }
}