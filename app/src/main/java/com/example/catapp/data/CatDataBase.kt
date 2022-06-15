package com.example.catapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.catapp.data.Daos.CatDao
import com.example.catapp.data.models.CatPhoto
import com.example.catapp.presenter.view.MainActivity
import kotlinx.coroutines.CoroutineScope

class CatDataBase {

    @Database(entities = [CatPhoto::class], version = 1, exportSchema = false)
    @TypeConverters(Converters::class)
    abstract class CatRoomDatabase : RoomDatabase() {
        abstract fun appDao(): CatDao

        companion object {
            @Volatile
            private var INSTANCE: CatRoomDatabase? = null

            fun getDataBase(context: Context): CatRoomDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CatRoomDatabase::class.java,
                        "cat_database"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }
}
