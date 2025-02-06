package com.justcircleprod.hhcloneem.domain.favouriteVacancy.useCase

import com.justcircleprod.hhcloneem.domain.favouriteVacancy.repository.FavouriteVacancyRepository
import javax.inject.Inject

class GetFavouriteVacanciesCountUseCase @Inject constructor(
    private val favouriteVacancyRepository: FavouriteVacancyRepository
) {

    operator fun invoke() = favouriteVacancyRepository.getCount()
}