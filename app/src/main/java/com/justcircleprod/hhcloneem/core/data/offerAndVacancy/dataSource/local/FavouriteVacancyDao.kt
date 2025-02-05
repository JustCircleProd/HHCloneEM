package com.justcircleprod.hhcloneem.core.data.offerAndVacancy.dataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.entity.FavouriteVacancyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteVacancyDao {

    @Query("SELECT * FROM favourite_vacancies")
    fun getAll(): Flow<List<FavouriteVacancyEntity>>

    @Query("SELECT COUNT(*) FROM favourite_vacancies")
    fun getCount(): Flow<Int>

    @Query("SELECT EXISTS(SELECT 1 FROM favourite_vacancies WHERE vacancyId = :vacancyId)")
    suspend fun isFavourite(vacancyId: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(favouriteVacancyEntity: FavouriteVacancyEntity)

    @Query("DELETE FROM favourite_vacancies WHERE vacancyId = :favouriteVacancyEntityId")
    suspend fun delete(favouriteVacancyEntityId: String)
}