package com.justcircleprod.hhcloneem.data.favouriteVacancy.repository

import com.justcircleprod.hhcloneem.data.favouriteVacancy.dataSource.local.FavouriteVacancyDao
import com.justcircleprod.hhcloneem.data.favouriteVacancy.mapper.mapToDataEntity
import com.justcircleprod.hhcloneem.data.favouriteVacancy.mapper.mapToDomainModels
import com.justcircleprod.hhcloneem.domain.favouriteVacancy.model.FavouriteVacancyModel
import com.justcircleprod.hhcloneem.domain.favouriteVacancy.repository.FavouriteVacancyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteVacancyRepositoryImpl @Inject constructor(
    private val favouriteVacancyDao: FavouriteVacancyDao
) : FavouriteVacancyRepository {

    override fun getAll(): Flow<List<FavouriteVacancyModel>> {
        return favouriteVacancyDao.getAll().map { it.mapToDomainModels() }
    }

    override fun getCount(): Flow<Int> {
        return favouriteVacancyDao.getCount()
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