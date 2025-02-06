package com.justcircleprod.hhcloneem.di

import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.dataSource.local.FavouriteVacancyDao
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.dataSource.remote.OfferAndVacancyApi
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.repository.FavouriteVacancyRepositoryImpl
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.repository.OfferAndVacancyRepositoryImpl
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.FavouriteVacancyRepository
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.OfferAndVacancyRepository
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