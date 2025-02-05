package com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository

import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.FavouriteVacancyModel
import kotlinx.coroutines.flow.Flow

interface FavouriteVacancyRepository {

    fun getAll(): Flow<List<FavouriteVacancyModel>>

    fun getCount(): Flow<Int>

    suspend fun isFavourite(vacancyId: String): Boolean

    suspend fun add(favouriteVacancyModel: FavouriteVacancyModel)

    suspend fun remove(vacancyId: String)
}