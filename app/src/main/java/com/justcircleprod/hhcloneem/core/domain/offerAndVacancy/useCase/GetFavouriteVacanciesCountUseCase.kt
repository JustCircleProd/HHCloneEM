package com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase

import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.FavouriteVacancyRepository
import javax.inject.Inject

class GetFavouriteVacanciesCountUseCase @Inject constructor(
    private val favouriteVacancyRepository: FavouriteVacancyRepository
) {

    operator fun invoke() = favouriteVacancyRepository.getCount()
}