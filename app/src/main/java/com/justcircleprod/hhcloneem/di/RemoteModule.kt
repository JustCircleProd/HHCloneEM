package com.justcircleprod.hhcloneem.di

import com.justcircleprod.hhcloneem.data.offerAndVacancy.dataSource.remote.OfferAndVacancyApi
import com.justcircleprod.hhcloneem.data.offerAndVacancy.dataSource.remote.OfferAndVacancyRetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideOfferAndVacancyApi(): OfferAndVacancyApi =
        OfferAndVacancyRetrofitClient.getInstance()
}