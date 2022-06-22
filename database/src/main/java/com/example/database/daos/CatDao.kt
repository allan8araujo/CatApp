package com.example.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.abstractions.CatPhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {
    @Query("SELECT * FROM cat_photos")
    fun getCatsFromDB(): Flow<MutableList<CatPhoto>>

    @Insert
    suspend fun insertCatInDB(cat: CatPhoto?)

    @Delete
    suspend fun deleteCatInDB(cat: CatPhoto?)

    @Query("DELETE FROM cat_photos")
    suspend fun deleteAllInDB(): Int
}
