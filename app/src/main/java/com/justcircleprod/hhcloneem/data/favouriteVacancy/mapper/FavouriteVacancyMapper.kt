package com.justcircleprod.hhcloneem.data.favouriteVacancy.mapper

import com.justcircleprod.hhcloneem.data.favouriteVacancy.entity.FavouriteVacancyEntity
import com.justcircleprod.hhcloneem.domain.favouriteVacancy.model.FavouriteVacancyModel

fun FavouriteVacancyEntity.mapToDomainModel() =
    FavouriteVacancyModel(vacancyId)

fun List<FavouriteVacancyEntity>.mapToDomainModels() =
    map { it.mapToDomainModel() }

fun FavouriteVacancyModel.mapToDataEntity() =
    FavouriteVacancyEntity(vacancyId)