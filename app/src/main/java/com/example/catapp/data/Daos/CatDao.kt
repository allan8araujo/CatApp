package com.example.catapp.data.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.catapp.data.models.CatPhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {
    @Query("SELECT * FROM cat_photos")
    fun getCatsFromDB(): Flow<List<CatPhoto>>

    @Insert
    suspend fun insertCat(cat: CatPhoto)

    @Query("DELETE FROM cat_photos")
    suspend fun deleteAll(): Int
}
