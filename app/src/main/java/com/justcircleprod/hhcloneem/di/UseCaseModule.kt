package com.justcircleprod.hhcloneem.di

import com.justcircleprod.hhcloneem.domain.favouriteVacancy.repository.FavouriteVacancyRepository
import com.justcircleprod.hhcloneem.domain.favouriteVacancy.useCase.GetFavouriteVacanciesCountUseCase
import com.justcircleprod.hhcloneem.domain.favouriteVacancy.useCase.ToggleFavouriteVacancyUseCase
import com.justcircleprod.hhcloneem.domain.offerAndVacancy.repository.OfferAndVacancyRepository
import com.justcircleprod.hhcloneem.domain.useCase.GetFavouriteVacanciesUseCase
import com.justcircleprod.hhcloneem.domain.useCase.GetVacanciesAndOffersUseCase
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