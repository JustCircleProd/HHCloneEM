package com.justcircleprod.hhcloneem.domain.favouriteVacancy.useCase

import com.justcircleprod.hhcloneem.domain.favouriteVacancy.model.FavouriteVacancyModel
import com.justcircleprod.hhcloneem.domain.favouriteVacancy.repository.FavouriteVacancyRepository
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