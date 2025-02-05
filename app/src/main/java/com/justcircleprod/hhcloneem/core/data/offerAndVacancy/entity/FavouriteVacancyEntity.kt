package com.justcircleprod.hhcloneem.core.data.offerAndVacancy.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_vacancies")
data class FavouriteVacancyEntity(
    @PrimaryKey val vacancyId: String
)