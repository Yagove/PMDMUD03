package com.utad.pmdmud03

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface LenguageDAO {

    @Query("SELECT * FROM tabla_lenguages")
    suspend fun getAll(): List<Lenguage>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(lenguage: Lenguage)

    @Delete
    suspend fun delete(lenguage: Lenguage)

    @Query("DELETE FROM tabla_lenguages")
    suspend fun deleteAll()


}