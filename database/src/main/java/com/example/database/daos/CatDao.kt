package com.example.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.abstractions.CatPhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {
    @Query("SELECT * FROM cat_photos")
    fun getCatsFromDB(): Flow<List<com.example.abstractions.CatPhoto>>

    @Insert
    suspend fun insertCatInDB(cat: com.example.abstractions.CatPhoto)

    @Query("DELETE FROM cat_photos")
    suspend fun deleteAllInDB(): Int
}
