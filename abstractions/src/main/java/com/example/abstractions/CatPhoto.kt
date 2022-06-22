package com.example.abstractions

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_photos")
data class CatPhoto(
    @ColumnInfo(name = "image") val image: Bitmap?,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
