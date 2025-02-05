package com.justcircleprod.hhcloneem.core.data.offerAndVacancy.repository

import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.dataSource.local.FavouriteVacancyDao
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.mapper.mapToDataEntity
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.mapper.mapToDomainModels
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.FavouriteVacancyModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.repository.FavouriteVacancyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteVacancyRepositoryImpl @Inject constructor(
    private val favouriteVacancyDao: FavouriteVacancyDao
) : FavouriteVacancyRepository {

    override fun getAll(): Flow<List<FavouriteVacancyModel>> {
        return favouriteVacancyDao.getAll().map { it.mapToDomainModels() }
    }

    override suspend fun isFavourite(vacancyId: String): Boolean {
        return favouriteVacancyDao.isFavourite(vacancyId)
    }

    override suspend fun add(favouriteVacancyModel: FavouriteVacancyModel) {
        favouriteVacancyDao.add(favouriteVacancyModel.mapToDataEntity())
    }

    override suspend fun remove(vacancyId: String) {
        favouriteVacancyDao.delete(vacancyId)
    }
}