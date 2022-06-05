package com.example.catapp.data.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.catapp.data.models.CatPhoto

@Dao
interface CatDao {
    @Insert
    suspend fun insertCat(cat: CatPhoto)

    @Query("SELECT * FROM cat_photos")
    suspend fun getCatsFromDB(): Long

    @Query("DELETE FROM cat_photos")
    suspend fun deleteAll(): Long
}
