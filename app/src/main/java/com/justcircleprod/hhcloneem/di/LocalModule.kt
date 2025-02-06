package com.justcircleprod.hhcloneem.di

import android.content.Context
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.dataSource.local.FavouriteVacancyDao
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.dataSource.local.FavouriteVacancyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideFavouriteVacancyDatabase(@ApplicationContext context: Context): FavouriteVacancyDatabase =
        FavouriteVacancyDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideFavouriteVacancyDao(favouriteVacancyDatabase: FavouriteVacancyDatabase): FavouriteVacancyDao =
        favouriteVacancyDatabase.favouriteVacancyDao()
}