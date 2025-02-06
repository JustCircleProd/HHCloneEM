package com.justcircleprod.hhcloneem.di

import com.justcircleprod.hhcloneem.data.favouriteVacancy.dataSource.local.FavouriteVacancyDao
import com.justcircleprod.hhcloneem.data.favouriteVacancy.repository.FavouriteVacancyRepositoryImpl
import com.justcircleprod.hhcloneem.data.offerAndVacancy.dataSource.remote.OfferAndVacancyApi
import com.justcircleprod.hhcloneem.data.offerAndVacancy.repository.OfferAndVacancyRepositoryImpl
import com.justcircleprod.hhcloneem.domain.favouriteVacancy.repository.FavouriteVacancyRepository
import com.justcircleprod.hhcloneem.domain.offerAndVacancy.repository.OfferAndVacancyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModel {

    @Singleton
    @Provides
    fun provideOfferAndVacancyRepository(api: OfferAndVacancyApi): OfferAndVacancyRepository =
        OfferAndVacancyRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideFavouriteVacancyRepository(favouriteVacancyDao: FavouriteVacancyDao): FavouriteVacancyRepository =
        FavouriteVacancyRepositoryImpl(favouriteVacancyDao)
}