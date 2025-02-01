package com.justcircleprod.hhcloneem.di

import com.justcircleprod.hhcloneem.core.data.offersAndVacancies.dataSource.remote.OffersAndVacanciesApi
import com.justcircleprod.hhcloneem.core.data.offersAndVacancies.dataSource.remote.OffersAndVacanciesRetrofitClient
import com.justcircleprod.hhcloneem.core.data.offersAndVacancies.repository.OffersAndVacanciesRepositoryImpl
import com.justcircleprod.hhcloneem.core.domain.repository.OffersAndVacanciesRepository
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
    fun provideOffersAndVacanciesApi(): OffersAndVacanciesApi =
        OffersAndVacanciesRetrofitClient.getInstance()

    @Singleton
    @Provides
    fun provideOffersAndVacanciesRepository(api: OffersAndVacanciesApi): OffersAndVacanciesRepository =
        OffersAndVacanciesRepositoryImpl(api)
}