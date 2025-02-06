package com.justcircleprod.hhcloneem.data.favouriteVacancy.dataSource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.justcircleprod.hhcloneem.data.favouriteVacancy.entity.FavouriteVacancyEntity
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(
    version = 1,
    entities = [FavouriteVacancyEntity::class]
)
abstract class FavouriteVacancyDatabase : RoomDatabase() {

    abstract fun favouriteVacancyDao(): FavouriteVacancyDao

    companion object {
        fun getInstance(@ApplicationContext context: Context): FavouriteVacancyDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                FavouriteVacancyDatabase::class.java,
                "favourite_vacancy_database"
            ).build()
        }
    }
}