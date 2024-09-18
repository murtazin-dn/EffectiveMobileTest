package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.VacancyDao
import com.example.database.entity.VacancyDbEntity

@Database(
    version = 1,
    entities = [
        VacancyDbEntity::class
    ]
)
internal abstract class HhDatabase : RoomDatabase() {
    abstract fun getVacancyDao(): VacancyDao
}