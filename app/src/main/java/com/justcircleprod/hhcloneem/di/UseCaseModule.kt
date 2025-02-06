package com.justcircleprod.hhcloneem.di

import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.FavouriteVacancyRepository
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.OfferAndVacancyRepository
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase.GetFavouriteVacanciesCountUseCase
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase.GetFavouriteVacanciesUseCase
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase.GetVacanciesAndOffersUseCase
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase.ToggleFavouriteVacancyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetVacanciesAndOffersUseCase(
        offerAndVacancyRepository: OfferAndVacancyRepository,
        favouriteVacancyRepository: FavouriteVacancyRepository
    ): GetVacanciesAndOffersUseCase =
        GetVacanciesAndOffersUseCase(offerAndVacancyRepository, favouriteVacancyRepository)

    @Singleton
    @Provides
    fun provideGetFavouriteVacanciesUseCase(
        offerAndVacancyRepository: OfferAndVacancyRepository,
        favouriteVacancyRepository: FavouriteVacancyRepository
    ): GetFavouriteVacanciesUseCase =
        GetFavouriteVacanciesUseCase(offerAndVacancyRepository, favouriteVacancyRepository)

    @Singleton
    @Provides
    fun provideToggleFavouriteVacancyUseCase(
        favouriteVacancyRepository: FavouriteVacancyRepository
    ): ToggleFavouriteVacancyUseCase =
        ToggleFavouriteVacancyUseCase(favouriteVacancyRepository)

    @Singleton
    @Provides
    fun provideGetFavouriteVacanciesCountUseCase(
        favouriteVacancyRepository: FavouriteVacancyRepository
    ): GetFavouriteVacanciesCountUseCase =
        GetFavouriteVacanciesCountUseCase(favouriteVacancyRepository)
}