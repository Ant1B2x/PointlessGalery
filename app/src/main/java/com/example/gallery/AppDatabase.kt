package com.example.gallery

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Picture::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pictureDao(): PictureDao
}
