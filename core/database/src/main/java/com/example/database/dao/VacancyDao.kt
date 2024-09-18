package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.VacancyDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VacancyDao {

    @Query(
        value = """
        SELECT * FROM vacancy
        WHERE id IN (:ids)
    """,
    )
    fun getVacancyEntities(ids: Set<String>): Flow<List<VacancyDbEntity>>

    @Query(
        value = """
        SELECT * FROM vacancy
        WHERE id = :id
    """,
    )
    fun getVacancy(id: String): Flow<VacancyDbEntity>

    @Query(
        value = """
        DELETE FROM vacancy
        WHERE id = :id
    """,
    )
    fun deleteVacancy(id: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertVacancy(vacancyDbEntity: VacancyDbEntity)

    @Query("SELECT count(*) FROM vacancy")
    fun getCount(): Flow<Int>


}