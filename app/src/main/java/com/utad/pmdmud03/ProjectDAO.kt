package com.utad.pmdmud03

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProjectDAO {

    @Query("SELECT * FROM tabla_projects")
    suspend fun getAll(): List<Project>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(project: Project)

    @Delete
    suspend fun delete(project: Project)

    @Query("DELETE FROM tabla_projects")
    suspend fun deleteAll()


}