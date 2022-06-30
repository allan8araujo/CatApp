package com.example.database.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.abstractions.CatPhoto

@Dao
interface CatDao {
    @Query("SELECT * FROM cat_photos ORDER BY id ASC")
    fun getCatsFromDB(): PagingSource<Int, CatPhoto>

    @Insert
    suspend fun insertCatInDB(cat: CatPhoto?)

    @Delete
    suspend fun deleteCatInDB(cat: CatPhoto?)

    @Query("DELETE FROM cat_photos")
    suspend fun deleteAllInDB(): Int
}
