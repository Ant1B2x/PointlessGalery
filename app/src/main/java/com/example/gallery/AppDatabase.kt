package com.example.gallery

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Picture::class), version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pictureDao(): PictureDao
}
