package com.example.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.abstractions.CatPhoto

@Dao
interface CatDao {
    @Query("SELECT * FROM cat_photos ORDER BY id DESC LIMIT :limit OFFSET :offset")
    suspend fun getCatsFromDB(limit: Int, offset: Int): List<CatPhoto>

    @Insert
    suspend fun insertCatInDB(cat: CatPhoto?)

    @Delete
    suspend fun deleteCatInDB(cat: CatPhoto?)

    @Query("DELETE FROM cat_photos")
    suspend fun deleteAllInDB(): Int
}
