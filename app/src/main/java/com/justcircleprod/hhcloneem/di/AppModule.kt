package com.justcircleprod.hhcloneem.di

import android.content.Context
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.dataSource.local.FavouriteVacancyDao
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.dataSource.local.FavouriteVacancyDatabase
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.dataSource.remote.OfferAndVacancyApi
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.dataSource.remote.OfferAndVacancyRetrofitClient
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.repository.FavouriteVacancyRepositoryImpl
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.repository.OfferAndVacancyRepositoryImpl
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.FavouriteVacancyRepository
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.OfferAndVacancyRepository
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase.GetFavouriteVacanciesUseCase
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase.GetVacanciesAndOffersUseCase
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase.ToggleFavouriteVacancyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOfferAndVacancyApi(): OfferAndVacancyApi =
        OfferAndVacancyRetrofitClient.getInstance()

    @Singleton
    @Provides
    fun provideOfferAndVacancyRepository(api: OfferAndVacancyApi): OfferAndVacancyRepository =
        OfferAndVacancyRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideFavouriteVacancyDatabase(@ApplicationContext context: Context): FavouriteVacancyDatabase =
        FavouriteVacancyDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideFavouriteVacancyDao(favouriteVacancyDatabase: FavouriteVacancyDatabase): FavouriteVacancyDao =
        favouriteVacancyDatabase.favouriteVacancyDao()

    @Singleton
    @Provides
    fun provideFavouriteVacancyRepository(favouriteVacancyDao: FavouriteVacancyDao): FavouriteVacancyRepository =
        FavouriteVacancyRepositoryImpl(favouriteVacancyDao)

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
}