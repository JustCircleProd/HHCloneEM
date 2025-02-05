package com.justcircleprod.hhcloneem.core.data.offerAndVacancy.mapper

import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.entity.FavouriteVacancyEntity
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.FavouriteVacancyModel

fun FavouriteVacancyEntity.mapToDomainModel() =
    FavouriteVacancyModel(vacancyId)

fun List<FavouriteVacancyEntity>.mapToDomainModels() =
    map { it.mapToDomainModel() }

fun FavouriteVacancyModel.mapToDataEntity() =
    FavouriteVacancyEntity(vacancyId)