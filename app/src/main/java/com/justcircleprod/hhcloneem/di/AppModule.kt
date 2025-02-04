package com.justcircleprod.hhcloneem.di

import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.dataSource.remote.OfferAndVacancyApi
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.dataSource.remote.OfferAndVacancyRetrofitClient
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.repository.OfferAndVacancyRepositoryImpl
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.OfferAndVacancyRepository
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.useCase.GetVacanciesAndOffersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideGetVacanciesAndOffersUseCase(offerAndVacancyRepository: OfferAndVacancyRepository): GetVacanciesAndOffersUseCase =
        GetVacanciesAndOffersUseCase(offerAndVacancyRepository)
}