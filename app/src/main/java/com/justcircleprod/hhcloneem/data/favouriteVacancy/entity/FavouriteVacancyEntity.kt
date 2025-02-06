package com.justcircleprod.hhcloneem.data.favouriteVacancy.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_vacancies")
data class FavouriteVacancyEntity(
    @PrimaryKey val vacancyId: String
)