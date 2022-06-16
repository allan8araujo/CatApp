package com.example.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.modelsdto.models.CatPhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {
    @Query("SELECT * FROM cat_photos")
    fun getCatsFromDB(): Flow<List<CatPhoto>>

    @Insert
    suspend fun insertCatInDB(cat: CatPhoto)

    @Query("DELETE FROM cat_photos")
    suspend fun deleteAllInDB(): Int
}
